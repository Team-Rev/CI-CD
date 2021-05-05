package rev.team.PROBLEM_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerChoice;
import rev.team.PROBLEM_SERVICE.domain.entity.AnswerDetail;

@Repository
public interface AnswerChoiceRepository extends JpaRepository<AnswerChoice, Long> {
}
