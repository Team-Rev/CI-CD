package rev.team.BOARD_SERVICE.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rev.team.BOARD_SERVICE.domain.entity.Board;
import rev.team.BOARD_SERVICE.util.Category;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query(value = "UPDATE `rev_problem`.`board` SET `title` = :title WHERE (`board_id` = :id);", nativeQuery = true)
    void updateById(Long id, String title);

    Page<Board> findAllByStatusAndCategory(Integer status, Category category, Pageable pageable);
}
