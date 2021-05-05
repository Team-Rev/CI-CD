package rev.team.PROBLEM_SERVICE.domain.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = {"answerMain", "choices"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@ToString(exclude = {"answerMain", "choices"})
@Builder

@Entity
@Table(name="answer_detail")
public class AnswerDetail {
    //기록 상세
    //한 문제씩 푸는것도 여기다가 저장할 예정

    @Id
    @Column(name = "answer_detail_id")
    private Long answerDetailId;

    private Long questionId; // 문제 ID

    private boolean isCorrect; //정답 여부 필요할 것 같음

    @ManyToOne
    @JoinColumn(name="answer_main_id")
    @JsonIgnore
    private AnswerMain answerMain;

    @OneToMany(mappedBy = "answerDetail")
    private Set<AnswerChoice> choices; // 선택한 답안

}
