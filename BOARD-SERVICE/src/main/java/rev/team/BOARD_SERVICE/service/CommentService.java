package rev.team.BOARD_SERVICE.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rev.team.BOARD_SERVICE.domain.entity.Comment;
import rev.team.BOARD_SERVICE.domain.repository.AskRepository;
import rev.team.BOARD_SERVICE.domain.repository.CommentRepository;

import javax.transaction.Transactional;
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
        askRepository.upReComments(comment.getRefAsk()); // 질문 글에 댓글 수 +1

        commentRepository.save(Comment.builder()
                .userId(comment.getUserId())
                .nickname(comment.getNickname())
                .comment(comment.getComment())
                .postDate(LocalDateTime.now())
                .refAsk(comment.getRefAsk())
                .build());
        return "OK";
    }

    public String delete(String userId, Long commentId, Long refAsk) {

        Comment comment = commentRepository.findById(commentId).get();

        if (comment.getUserId() == userId) {
            askRepository.downReComments(refAsk); // 질문 글에 댓글 수 -1
            commentRepository.deleteById(commentId);

            return "OK";
        }
        return "not matched user or not existed comment";
    }

    public List<Comment> getComments(Long askId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "commentId");
        return commentRepository.findAllByRefAsk(askId, pageable);
    }
}
