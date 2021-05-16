package rev.team.POINT_SERVICE.domain.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointRecordRequest {

    private Long reasonId;

    private String userId;

}
