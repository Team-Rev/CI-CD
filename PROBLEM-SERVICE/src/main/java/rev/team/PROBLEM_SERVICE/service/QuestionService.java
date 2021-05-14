package rev.team.PROBLEM_SERVICE.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rev.team.PROBLEM_SERVICE.domain.dto.SubmitDTO;
import rev.team.PROBLEM_SERVICE.domain.entity.*;
import rev.team.PROBLEM_SERVICE.domain.repository.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MultipleChoiceRepository multipleChoiceRepository;
    private final AnswerMainRepository answerMainRepository;
    private final AnswerDetailRepository answerDetailRepository;
    private final AnswerChoiceRepository answerChoiceRepository;
    private final AnswerTotalRepository answerTotalRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository
            , MultipleChoiceRepository multipleChoiceRepository
            , AnswerMainRepository answerMainRepository
            , AnswerDetailRepository answerDetailRepository
            , AnswerChoiceRepository answerChoiceRepository
            , AnswerTotalRepository answerTotalRepository){

        this.questionRepository = questionRepository;
        this.multipleChoiceRepository = multipleChoiceRepository;
        this.answerMainRepository = answerMainRepository;
        this.answerDetailRepository = answerDetailRepository;
        this.answerChoiceRepository = answerChoiceRepository;
        this.answerTotalRepository = answerTotalRepository;
    }

    //한 문제 가져오기
    public Question getQuestion(Long id){
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getRandomQuestions(){
        //TODO: 랜덤으로 하나씩 문제 풀꺼 범위 가져오기(풀었던 문제 억제, 취약점 반영)
        return new ArrayList<>();
    }
    public List<Question> getKeywordQuestions() {
        //TODO: 원하는 키워드 문제 가져오기(풀었던 문제 억제)
        return new ArrayList<>();
    }

    public List<Question> getTestQuestion() {
        //TODO: 처음 진단 평가(?)용 문제 가져오기
        return new ArrayList<>();
    }

    public AnswerMain submitQuestions(SubmitDTO submit) {
        Set<Submit> submits = submit.getSubmitList();
        Set<AnswerDetail> details = new HashSet<>();

        AnswerMain main = AnswerMain.builder()
            .answerMainId(answerMainRepository.count()+1)
            .userId(submit.getUserId())
            .date(LocalDateTime.now())
            .totalCount(submits.size())
            .build();
        answerMainRepository.saveAndFlush(main);
        boolean nowCorrect;
        Set<AnswerChoice> answerChoiceHashSet;
        for(Submit nowSubmit : submits) {
            answerChoiceHashSet = new HashSet<>();

            nowCorrect = nowSubmit.getMultipleChoiceIds().size() == multipleChoiceRepository.findAllByQuestionIdAndIsCorrect(nowSubmit.getQuestionId(), true).size();
            AnswerDetail detail = AnswerDetail.builder()
                    .answerDetailId(answerDetailRepository.count()+1)
                    .questionId(nowSubmit.getQuestionId())
                    .answerMain(main)
                    .build();
            answerDetailRepository.saveAndFlush(detail);
            for(Long choiceId : nowSubmit.getMultipleChoiceIds()){
                MultipleChoice choice = multipleChoiceRepository.findByIdAndQuestionId(choiceId, nowSubmit.getQuestionId()).orElse(null);
                if(choice == null){
                    nowCorrect = false;
                    break;
                }
                nowCorrect = nowCorrect && choice.getIsCorrect();
                AnswerChoice answerChoice = AnswerChoice.builder()
                        .answerChoiceId(answerChoiceRepository.count()+1)
                        .answerDetail(detail)
                        .multipleChoiceId(choiceId)
                        .build();
                answerChoiceRepository.saveAndFlush(answerChoice);

                answerChoiceHashSet.add(answerChoice);
            }
            detail.setCorrect(nowCorrect);
            detail.setChoices(answerChoiceHashSet);
            answerDetailRepository.saveAndFlush(detail);
            details.add(detail);
        }
        main.setCorrectCount((int)details.stream().filter(AnswerDetail::isCorrect).count());
        main.setDetails(details);
        answerMainRepository.saveAndFlush(main);
        updateTotal(main.getUserId(), main.getTotalCount(), main.getCorrectCount());
        saveCategory(main);
        return main;
    }

    //TODO: 제출 시에 기록 종합 계속 업데이트
    private void updateTotal(String userId, Integer total, Integer correct){
        Optional<AnswerTotal> answerTotal = answerTotalRepository.findById(userId);
        if(answerTotal.isPresent()){
            AnswerTotal now = answerTotal.get();
            now.setExamCount(now.getExamCount()+1);
            now.setTotalQuestionCount(now.getTotalQuestionCount()+total);
            now.setTotalCorrectCount(now.getTotalCorrectCount()+correct);
            now.setCorrectAverage( (int)((float)now.getTotalCorrectCount()/ (float)now.getTotalQuestionCount() *100) );// 다시 평균 계산
            answerTotalRepository.save(now);
        }else{
            answerTotalRepository.save(AnswerTotal.builder()
                    .userId(userId)
                    .examCount(1)
                    .totalQuestionCount(total)
                    .totalCorrectCount(correct)
                    .correctAverage((int) ((float) correct / (float)total * 100))
                    .build());
        }
    }

    //TODO: 문제 제출시에 AnswerMain에 핵심 카테고리 생성
    public void saveCategory(AnswerMain answerMain){
        HashMap<String, Integer> mainCategory = new HashMap<>();
        HashMap<String, Integer> subCategory = new HashMap<>();

        for(AnswerDetail detail : answerMain.getDetails()){
            Question question = questionRepository.findById(detail.getQuestionId()).orElse(null);
            assert question != null;
            mainCategory.put(question.getMainCategory(), mainCategory.getOrDefault(question.getMainCategory(), 0)+1);
            subCategory.put(question.getSubCategory(), subCategory.getOrDefault(question.getSubCategory(), 0)+1);
        }
        Integer max = 0;
        String result = "";
        for(String key : mainCategory.keySet()){
            Integer now = mainCategory.get(key);
            if(now > max) result = key;
        }
        answerMain.setMainCategory(result);
        max = 0;
        result = "";
        for(String key : subCategory.keySet()){
            Integer now = subCategory.get(key);
            if(now > max) result = key;
        }
        answerMain.setSubCategory(result);
        answerMainRepository.save(answerMain);
    }

    public void submitQuestion(Long id, Question question) {
        //TODO: 해당 문제 정답 체크해서 기록하기
    }

    //문제 ID 범위로 가져오기
    public List<Question> rangeQuestions(Long start ,Long end){
        return questionRepository.findByIdIsBetween(start,end);
    }

    //문제 ID 선택해서 가져오기
    public List<Question> getSelectQuestions(Set<Long> ids) {
        System.out.println(Arrays.toString(ids.toArray()) );
        List<Question> questions = new ArrayList<>();
        for(Long id : ids) questionRepository.findById(id).ifPresent(questions::add);

        return questions;
    }
}
