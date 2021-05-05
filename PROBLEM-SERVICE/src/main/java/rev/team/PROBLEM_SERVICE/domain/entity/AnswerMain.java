package rev.team.PROBLEM_SERVICE.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = "details")
@ToString(exclude = "details")
@Builder
@Entity
@Table(name="answer_main")
public class AnswerMain {
    //기록 메인

    @Id
    @Column(name = "answer_main_id")
    private Long answerMainId;

    private String userId; // 푼 사용자 ID

    private LocalDateTime date; // 문제 푼 날

    private int totalCount;// 총 문제 갯수

    private int correctCount; // 맞힌 문제 갯수

    @OneToMany(mappedBy = "answerMain")
    private Set<AnswerDetail> details; // 선택한 답안
}
