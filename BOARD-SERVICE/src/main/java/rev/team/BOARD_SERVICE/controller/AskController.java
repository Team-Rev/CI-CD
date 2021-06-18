package rev.team.BOARD_SERVICE.controller;

import org.springframework.web.bind.annotation.*;
import rev.team.BOARD_SERVICE.domain.dto.AskPageDTO;
import rev.team.BOARD_SERVICE.domain.entity.Ask;
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

    //TODO: GET ASK PAGE. 페이지 별로 보여질 질문 요약
    @PostMapping("")
    public void createAsk(@RequestBody Ask ask){
        askService.createAsk(ask);
    }

    //TODO: GET FREQUENCY ASK. 많이 본 질문 6개(반정규화 해야됨)
    @GetMapping("/top")
    public List<Ask> getAskOfFrequency(){
        return new ArrayList<>();
    }

    //TODO: 작성
    //TODO: 수정
    //TODO: 삭제
}
