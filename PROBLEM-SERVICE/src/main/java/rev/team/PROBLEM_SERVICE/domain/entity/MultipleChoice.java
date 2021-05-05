package rev.team.PROBLEM_SERVICE.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "multiple_choice")
public class MultipleChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isCorrect;

    @Column(columnDefinition = "TEXT")
    private String choice;

    @Column(name = "question_id")
    private Long questionId;

}
