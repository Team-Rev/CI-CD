package rev.team.POINT_SERVICE.domain.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointDTO {

    private int point;

    private String reason;
}
