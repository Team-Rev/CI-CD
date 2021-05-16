package rev.team.POINT_SERVICE.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PointReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointReasonId;

    @NonNull
    private String reason;

    @NonNull
    private int point;
}