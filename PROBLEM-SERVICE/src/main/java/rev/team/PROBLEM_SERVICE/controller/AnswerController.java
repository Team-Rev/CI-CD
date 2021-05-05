package rev.team.PROBLEM_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerMain;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerSummary;
import rev.team.PROBLEM_SERVICE.domain.repository.AnswerMainRepository;
import rev.team.PROBLEM_SERVICE.service.AnswerService;

import java.util.List;

@RestController
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @GetMapping("/answer/{id}")
    public AnswerMain getAnswerRecord(@PathVariable("id") Long id){
        return answerService.getAnswerRecord(id);
    }

    @GetMapping("/answer/summary")
    public List<AnswerSummary> getAnswerSummary(@RequestParam("id") String id){
        return answerService.getAnswerSummary(id);
    }
}
