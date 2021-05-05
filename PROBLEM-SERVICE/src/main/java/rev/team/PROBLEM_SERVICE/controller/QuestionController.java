package rev.team.PROBLEM_SERVICE.controller;

import org.springframework.web.bind.annotation.*;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerMain;
import rev.team.PROBLEM_SERVICE.domain.entity.Question;
import rev.team.PROBLEM_SERVICE.domain.entity.SubmitDTO;
import rev.team.PROBLEM_SERVICE.service.QuestionService;

import java.util.List;
import java.util.Set;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    //TODO: 정답 받아오는 객체 만들어야 됨

    //TEST CONTROLLER: 아이디로 문제 가져오기
    @GetMapping("/question/{id}")
    public Question getQuestion(@PathVariable("id") Long id){
        Question question = questionService.getQuestion(id);
        if(question == null) return new Question();
        else return question;
    }

    //한 문제씩 정답 제출
    @PostMapping("/question/{id}")
    public String submitQuestion(@PathVariable("id") Long id, @RequestBody Question question){
        questionService.submitQuestion(id, question);
        return "200"; //맞았는지 틀렸는지 보내야 함
    }

    //랜덤으로 풀 문제 리스트
    @GetMapping("/randomQuestions")
    public List<Question> getRandomQuestions(){
        return questionService.getRandomQuestions();
    }

    //키워드 정하거나 섞어서 풀기
    @GetMapping("/keywordQuestions")
    public List<Question> getKeywordQuestions(@RequestBody Set<String> keywords){
        return questionService.getKeywordQuestions();
    }

    //진단 평가용 문제 받아오기
    @GetMapping("/testQuestions")
    public List<Question> getTestQuestions(){
        return questionService.getTestQuestion();
    }

    //문제 제출
    @PostMapping("/submit")
    public AnswerMain submitTestQuestions(@RequestBody SubmitDTO answers){
        return questionService.submitQuestions(answers);
    }

    //아이디 범위 정해서 문제 가져오기
    @GetMapping("/rangeQuestions")
    public List<Question> rangeQuestions(@RequestParam("start") Long start, @RequestParam("end") Long end){
        return questionService.rangeQuestions(start, end);
    }

    //아이디 리스트 받아서 문제 가져오기
    @GetMapping("/selectQuestions")
    public List<Question> selectQuestions(@RequestParam("ids") Set<Long> ids){
        return questionService.getSelectQuestions(ids);
    }
}
