package rev.team.PROBLEM_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rev.team.PROBLEM_SERVICE.domain.dto.AnswerDetailDTO;
import rev.team.PROBLEM_SERVICE.domain.entity.*;
import rev.team.PROBLEM_SERVICE.domain.mapping.AnswerSummary;
import rev.team.PROBLEM_SERVICE.domain.repository.AnswerMainRepository;
import rev.team.PROBLEM_SERVICE.domain.repository.AnswerTotalRepository;
import rev.team.PROBLEM_SERVICE.domain.repository.QuestionRepository;

import java.util.*;

@Service
public class AnswerService {

    private final AnswerMainRepository answerMainRepository;
    private final QuestionRepository questionRepository;
    private final AnswerTotalRepository answerTotalRepository;

    @Autowired
    public AnswerService(AnswerMainRepository answerMainRepository, QuestionRepository questionRepository, AnswerTotalRepository answerTotalRepository){
        this.answerMainRepository = answerMainRepository;
        this.questionRepository = questionRepository;
        this.answerTotalRepository = answerTotalRepository;
    }

    public AnswerMain getAnswerRecord(Long id){
        return answerMainRepository.findById(id).orElse(null);
    }

    public List<ResultSummary> getResult(String id) {
        List<AnswerMain> answerMains = answerMainRepository.findAllByUserIdOrderByAnswerMainIdDesc(id);
        List<ResultSummary> result = new ArrayList<>();

        for(AnswerMain answerMain : answerMains){
            ResultSummary now = new ResultSummary();
            now.setAnswerMain(answerMain);
            for(AnswerDetail detail : answerMain.getDetails()){
                Question question = questionRepository.findById(detail.getQuestionId()).orElse(null);
                assert question != null;
                now.getCategoryMain().add(question.getMainCategory());
                now.getCategorySub().add(question.getSubCategory());
            }
            result.add(now);
        }
        return result;
    }

    //???????????? ?????? ?????? ???????????????
    public AnswerTotal getTotal(String id) {
        return answerTotalRepository.findById(id).orElse(null);
    }

    public List<AnswerSummary> getAnswerSummary(String id, Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "answerMainId");
        return answerMainRepository.findAllByUserId(id, pageable);
    }

    public List<AnswerDetailDTO> getDetail(Long id) {
        //????????? ???????????? ?????? ?????????
        Optional<AnswerMain> mainOptional = answerMainRepository.findById(id);
        AnswerMain main;
        List<AnswerDetailDTO> answerDetailDTOS = new ArrayList<>();
        if(mainOptional.isPresent()) {
            main = mainOptional.get();
            // ?????? ?????? ????????? ????????? ????????? ??????
            for(AnswerDetail detail : main.getDetails()){
                AnswerDetailDTO answerDetailDTO = new AnswerDetailDTO();

                // ??????????????? ?????? ???????????? ?????? ???????????? AnswerDetailDTO ????????? ??????
                Optional<Question> questionOptional = questionRepository.findById(detail.getQuestionId());
                questionOptional.ifPresent(answerDetailDTO::setQuestion);

                // ??????????????? choices ???????????? multipleChoiceId??? AnswerDetailDTO ????????? ??????
                Set<Long> choices = new HashSet<>();
                for(AnswerChoice choice : detail.getChoices()) choices.add(choice.getMultipleChoiceId());
                answerDetailDTO.setChoices(choices);

                // AnswerDetailDTO ???????????? ????????? ??????
                answerDetailDTOS.add((answerDetailDTO));
            }
        }
        return answerDetailDTOS;
    }
}
