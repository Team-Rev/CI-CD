package rev.team.PROBLEM_SERVICE.domain.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ResultSummary {
    AnswerMain answerMain;
    Set<String> categoryMain = new HashSet<>();
    Set<String> categorySub = new HashSet<>();
}
