package rev.team.BOARD_SERVICE.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rev.team.BOARD_SERVICE.domain.entity.Comment;
import rev.team.BOARD_SERVICE.domain.repository.AskRepository;
import rev.team.BOARD_SERVICE.domain.repository.CommentRepository;

import javax.transaction.Transactional;
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
    public Comment create(Comment comment) {

        comment.setCommentId(commentRepository.count()+1);
        if(comment.getRefComment() == null) comment.setRefComment(comment.getCommentId());
        askRepository.updateReComments(comment.getRefAsk());
        return commentRepository.save(comment);
    }

    public List<Comment> getComments(Long askId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "commentId");
        return commentRepository.findAllByRefAsk(askId, pageable);
    }
}
