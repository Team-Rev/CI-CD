package rev.team.BOARD_SERVICE.service;

import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rev.team.BOARD_SERVICE.domain.entity.Comment;
import rev.team.BOARD_SERVICE.domain.repository.AskRepository;
import rev.team.BOARD_SERVICE.domain.repository.CommentRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AskRepository askRepository;


    public CommentService(CommentRepository commentRepository, AskRepository askRepository) {
        this.commentRepository = commentRepository;
        this.askRepository = askRepository;
    }

    @Transactional
    public String create(Comment comment) {
        askRepository.updateReComments(comment.getRefAsk());

        /*if(comment.getRefComment() == null) {
            comment.setRefComment(comment.getCommentId());
        }
        */
        comment.setCommentId(commentRepository.count() + 1);
        commentRepository.save(comment);

        return "OK";
    }

    public String delete(Long commentId) {
        commentRepository.deleteById(commentId);

        return "SUCCESS";
    }

    public List<Comment> getComments(Long askId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "commentId");
        return commentRepository.findAllByRefAsk(askId, pageable);
    }
}
