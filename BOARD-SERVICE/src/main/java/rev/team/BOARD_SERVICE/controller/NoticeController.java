package rev.team.BOARD_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rev.team.BOARD_SERVICE.domain.dto.NoticeSetUpDTO;
import rev.team.BOARD_SERVICE.domain.dto.NoticeSummaryDTO;
import rev.team.BOARD_SERVICE.domain.dto.PageNoticeSummaryDTO;
import rev.team.BOARD_SERVICE.service.NoticeService;

import java.util.List;

@RestController
public class NoticeController {

    //TODO : 게시판 별로 테이블 나누기
    //공지글(2021-05-12~ing, 김태영)

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    //고정 된 글 한번에 다 가져오기(정렬 포함) Completed at 2021-05-12 17:44
    @GetMapping("/notice-pined")
    public List<NoticeSummaryDTO> getPinedNoticeSummaries(){
        return noticeService.getPinedNoticeSummaries();
    }

    //고정 안 된 글 전체 페이지 갯수 구해서 전송, 1개 페이지는 10개 글
    @GetMapping("/notice")
    public PageNoticeSummaryDTO getNoticeSummaries(@RequestParam("page") Integer page){
        return noticeService.getNoticeSummaries(page);
    }

    //처음 페이지 로딩 시 불러올 데이터
    @GetMapping("/notice-first")
    public NoticeSetUpDTO getFirstNoticeSummary(@RequestParam("page") Integer page){
        return noticeService.getSetUpNoticeSummary(page);
    }

    //게시글 내용만 불러오기
    @GetMapping("/notice-content")
    public String getNoticeContent(@RequestParam("id") Long id){
        return noticeService.getNoticeContent(id);
    }
}
