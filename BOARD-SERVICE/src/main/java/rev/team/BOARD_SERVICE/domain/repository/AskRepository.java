package rev.team.BOARD_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rev.team.BOARD_SERVICE.domain.entity.Ask;

@Repository
public interface AskRepository extends JpaRepository<Ask, Long> {
    @Modifying
    @Query(value = "UPDATE Ask SET hits = hits+1 WHERE askId = :id")
    void hits(@Param("id")Long id);

    @Modifying
    @Query(value = "UPDATE Ask SET comments = comments +1 WHERE askId = :id")
    void updateReComments(@Param("id")Long id);
}
