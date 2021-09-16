package rev.team.PROBLEM_SERVICE.domain.dto;

import rev.team.PROBLEM_SERVICE.domain.entity.AnswerChoice;
import rev.team.PROBLEM_SERVICE.domain.entity.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AnswerDetailDTO {
    private Question question;
    private Set<Long> choices;
}
