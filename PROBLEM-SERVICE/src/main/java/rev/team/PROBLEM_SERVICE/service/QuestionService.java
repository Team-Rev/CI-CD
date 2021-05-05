package rev.team.PROBLEM_SERVICE.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rev.team.PROBLEM_SERVICE.domain.entity.*;
import rev.team.PROBLEM_SERVICE.domain.repository.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MultipleChoiceRepository multipleChoiceRepository;
    private final AnswerMainRepository answerMainRepository;
    private final AnswerDetailRepository answerDetailRepository;
    private final AnswerChoiceRepository answerChoiceRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository
            , MultipleChoiceRepository multipleChoiceRepository
            , AnswerMainRepository answerMainRepository
            , AnswerDetailRepository answerDetailRepository, AnswerChoiceRepository answerChoiceRepository){

        this.questionRepository = questionRepository;
        this.multipleChoiceRepository = multipleChoiceRepository;
        this.answerMainRepository = answerMainRepository;
        this.answerDetailRepository = answerDetailRepository;
        this.answerChoiceRepository = answerChoiceRepository;
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
            nowCorrect = true;
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
        return main;
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
