package rev.team.BOARD_SERVICE.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rev.team.BOARD_SERVICE.domain.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByRefAsk(Long askId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.commentId = :commentId AND c.userId = :userId")
    boolean findCommentByUserId(String userId, Long commentId);
}
