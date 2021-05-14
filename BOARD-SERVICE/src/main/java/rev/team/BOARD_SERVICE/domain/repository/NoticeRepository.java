package rev.team.BOARD_SERVICE.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rev.team.BOARD_SERVICE.domain.entity.Notice;
import rev.team.BOARD_SERVICE.domain.mapping.NoticeSummary;

import java.util.Set;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    //고정 된 공지글 한번에 다 가져오기 (내용은 제외하고)
    Set<NoticeSummary> findAllByPined(Boolean pined, Sort sort);

    //고정 여부에 따른 공지글 페이지로 가져오기 (내용은 제외하고)
    Page<NoticeSummary> findAllByPined(Boolean pined, Pageable pageable);

    //고정 여부에 따른 게시글 카운트
    @Query(value = "select count(*) from rev_problem.notice where pined=:pined", nativeQuery = true)
    Long countByPined(@Param("pined") Boolean pined);

    @Modifying
    @Query(value = "UPDATE Notice SET hits = hits+1 WHERE noticeId = :id")
    void hits(@Param("id")Long id);
}
