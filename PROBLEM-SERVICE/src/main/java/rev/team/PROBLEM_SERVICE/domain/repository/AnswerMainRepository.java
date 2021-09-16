package rev.team.PROBLEM_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerMain;
import rev.team.PROBLEM_SERVICE.domain.mapping.AnswerSummary;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface AnswerMainRepository extends JpaRepository<AnswerMain, Long> {

    List<AnswerMain> findAllByUserIdOrderByAnswerMainIdDesc(String userId);

    @Modifying
    @Query("UPDATE AnswerMain main SET main.correctCount = :count WHERE main.answerMainId = :id")
    void updateCorrect(int count, Long id);

    List<AnswerSummary> findAllByUserId(String userId, Pageable pageable);
}
