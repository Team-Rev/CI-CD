package rev.team.PROBLEM_SERVICE.domain.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = "answerDetail")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@ToString(exclude = "answerDetail")
@Builder
@Entity
@Table(name = "answer_choice")
public class AnswerChoice {

    @Id
    @Column(name = "answer_choice_id")
    private Long answerChoiceId;

    private Long multipleChoiceId;

    @ManyToOne
    @JoinColumn(name="answer_detail_id")
    @JsonIgnore
    private AnswerDetail answerDetail;

}
