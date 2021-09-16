package rev.team.PROBLEM_SERVICE.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "answer_total")
public class AnswerTotal {

    @Id
    private String userId;

    private Integer examCount;
    private Integer totalCorrectCount;
    private Integer totalQuestionCount;
    private Integer correctAverage; //0~100
}
