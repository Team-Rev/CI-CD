package rev.team.PROBLEM_SERVICE.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rev.team.PROBLEM_SERVICE.domain.entity.MultipleChoice;

import java.util.Optional;

@Repository
public interface MultipleChoiceRepository extends JpaRepository<MultipleChoice, Long> {

    Optional<MultipleChoice> findByIdAndQuestionId(Long id, Long questionId);
}
