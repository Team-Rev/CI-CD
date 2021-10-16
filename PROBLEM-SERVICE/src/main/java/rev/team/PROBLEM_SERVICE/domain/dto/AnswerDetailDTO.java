package rev.team.PROBLEM_SERVICE.domain.dto;

import rev.team.PROBLEM_SERVICE.domain.entity.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AnswerDetailDTO {
    private Question question;
    private Set<Long> choices;
    private List<String> keywords;
}
