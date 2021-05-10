package rev.team.PROBLEM_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerDetail;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerMain;
import rev.team.PROBLEM_SERVICE.domain.entity.Question;
import rev.team.PROBLEM_SERVICE.domain.entity.ResultSummary;
import rev.team.PROBLEM_SERVICE.domain.repository.AnswerMainRepository;
import rev.team.PROBLEM_SERVICE.domain.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswerMainRepository answerMainRepository;
    private final QuestionRepository questionRepository;
    @Autowired
    public AnswerService(AnswerMainRepository answerMainRepository, QuestionRepository questionRepository){
        this.answerMainRepository = answerMainRepository;
        this.questionRepository = questionRepository;
    }

    public AnswerMain getAnswerRecord(Long id){
        return answerMainRepository.findById(id).orElse(null);
    }

    public List<AnswerMain> getAnswerSummary(String id) {
        return answerMainRepository.findAllByUserIdOrderByAnswerMainIdDesc(id);
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
}
