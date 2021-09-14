package rev.team.BOARD_SERVICE.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rev.team.BOARD_SERVICE.domain.dto.AskPageDTO;
import rev.team.BOARD_SERVICE.domain.entity.Ask;
import rev.team.BOARD_SERVICE.domain.entity.Comment;
import rev.team.BOARD_SERVICE.domain.request.AskUpdateReq;
import rev.team.BOARD_SERVICE.service.AskService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ask")
public class AskController {

    private final AskService askService;

    public AskController(AskService askService){
        this.askService = askService;
    }

    //TODO: GET ASK PAGE. 페이지 별로 보여질 질문 요약
    @GetMapping("")
    public AskPageDTO getAskPage(@RequestParam int page){
        return askService.getAskPage(page);
    }

    //TODO: GET ASK DETAIL. 질문의 내용과 댓글들
    @GetMapping("/{id}")
    public Ask getAsk(@PathVariable("id") Long id){
        return askService.getAsk(id);
    }

    //TODO: 조회수 기준 상위 6개 질문
    @GetMapping("/top")
    public List<Ask> getAskOfFrequency(){
        return askService.getAskOfFrequency();
    }

    //TODO: 질문 작성
    @PostMapping("")
    public String createAsk(@RequestBody Ask ask){
        return askService.createAsk(ask);
    }

    //TODO: 수정
    @PatchMapping("")
    public String updateAsk(@RequestBody AskUpdateReq askUpdateReq) {
        return askService.updateAsk(askUpdateReq);
    }

    //TODO: 삭제
    @DeleteMapping("")
    public String deleteAsk(@RequestParam Long askId) {
        return askService.deleteAsk(askId);
    }
}
