package rev.team.PROBLEM_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rev.team.PROBLEM_SERVICE.domain.dto.AnswerDetailDTO;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerMain;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerTotal;
import rev.team.PROBLEM_SERVICE.domain.entity.ResultSummary;
import rev.team.PROBLEM_SERVICE.domain.mapping.AnswerSummary;
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

    @GetMapping("/answer/result")
    public List<ResultSummary> getResult(@RequestParam("id") String id){
        return answerService.getResult(id);
    }

    // 김태영 CREATED AT 2021-05-14 시험 결과 메인 정보 페이지 별로 가져오기
    @GetMapping("/answer/summary")
    public List<AnswerSummary> getAnswerSummary(@RequestParam("id") String id, @RequestParam("page") Integer page){
        return answerService.getAnswerSummary(id, page);
    }
    // 김태영 CREATED AT 2021-05-14 전체 시험 결과 요약 정보 가져오기
    @GetMapping("/answer/total")
    public AnswerTotal getTotal(@RequestParam("id") String id){
        return answerService.getTotal(id);
    }

    // 김태영 CREATED AT 2021-05-14 결과 메인에서 디테일하게 보고싶은 메인의 ID를 이용해서 조회할 수 있음
    @GetMapping("/answer/detail")
    public List<AnswerDetailDTO> getDetail(@RequestParam("id") Long id){ // AnswerMainId
        return answerService.getDetail(id);
    }

}
