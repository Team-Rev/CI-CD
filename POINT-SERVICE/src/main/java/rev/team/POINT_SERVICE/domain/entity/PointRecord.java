package rev.team.POINT_SERVICE.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PointRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // n:1 관계
    @JoinColumn(name = "pointReasonId") // 참조키
    private PointReason pointReasonId;

    @NonNull
    private String userId;

    @NonNull
    private LocalDateTime pointDate;
}