package rev.team.PROBLEM_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerMain;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerSummary;
import rev.team.PROBLEM_SERVICE.domain.entity.Question;

import java.util.List;

@Repository
public interface AnswerMainRepository extends JpaRepository<AnswerMain, Long> {

    List<AnswerMain> findAllByUserIdOrderByAnswerMainIdDesc(String userId);

    @Modifying
    @Query("UPDATE AnswerMain main SET main.correctCount = :count WHERE main.id = :id")
    void updateCorrect(int count, Long id);
}
