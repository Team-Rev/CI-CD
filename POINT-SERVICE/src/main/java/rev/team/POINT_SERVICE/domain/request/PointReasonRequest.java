package rev.team.POINT_SERVICE.domain.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointReasonRequest {

    private int point;

    private String reason;
}
