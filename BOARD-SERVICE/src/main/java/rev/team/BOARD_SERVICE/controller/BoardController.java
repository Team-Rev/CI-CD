package rev.team.BOARD_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rev.team.BOARD_SERVICE.domain.entity.Board;
import rev.team.BOARD_SERVICE.domain.dto.BoardDTO;
import rev.team.BOARD_SERVICE.service.BoardService;

import java.util.List;


@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Board> getPost(@PathVariable("id") Long id){
        return boardService.getPost(id);
    }

    @GetMapping("/post-main")
    public List<BoardDTO> getMainPosts(){
        return boardService.getMainPosts();
    }

    @PostMapping("/post")
    public ResponseEntity<Board> createPost(@RequestBody Board board){
        return ResponseEntity.ok(boardService.save(board));
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable("id") Long id){
        return boardService.deletePost(id);
    }

    @PatchMapping("/post/{id}")
    public String updatePost(@PathVariable("id") Long id, @RequestParam("title")String title){
        return boardService.updatePost(id, title);
    }

    //TODO : 게시판 별로 테이블 나누기
    // 공지글(2021-05-12~ing, 김태영)
    // 질문글
    // 문의글


    //TODO : 글 세부내역 조회하면 조회수올리면서 해당 아이디의 글 반환

}
