package rev.team.BOARD_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rev.team.BOARD_SERVICE.domain.entity.Ask;

import java.util.List;

@Repository
public interface AskRepository extends JpaRepository<Ask, Long> {
    @Modifying
    @Query(value = "UPDATE Ask SET hits = hits+1 WHERE askId = :id")
    void hits(@Param("id")Long id);

    @Modifying
    @Query(value = "UPDATE Ask SET comments = comments + 1 WHERE askId = :id")
    void upReComments(@Param("id")Long id);

    @Modifying
    @Query(value = "UPDATE Ask SET comments = comments - 1 WHERE askId = :id")
    void downReComments(@Param("id")Long id);

    @Query(value = "SELECT * FROM ask ORDER BY hits DESC LIMIT 6;", nativeQuery = true)
    List<Ask> findAllDesc();
}
