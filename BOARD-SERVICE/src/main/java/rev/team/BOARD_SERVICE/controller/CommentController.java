package rev.team.BOARD_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rev.team.BOARD_SERVICE.domain.entity.Comment;
import rev.team.BOARD_SERVICE.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    //TODO : 조회
    @GetMapping("")
    public List<Comment> getComments(@RequestParam("askId") Long askId, @RequestParam("page") Integer page){
        return commentService.getComments(askId, page);
    }

    //작성
    @PostMapping("")
    public ResponseEntity<Comment> create(@RequestBody Comment comment){
        Comment newComment = commentService.create(comment);
        return new ResponseEntity<>(newComment, HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("")
    public String delete(@RequestParam("commentId") Long commentId){
        return commentService.delete(commentId);
    }
}
