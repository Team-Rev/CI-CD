package rev.team.POINT_SERVICE.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPointRecordRes {

    private String reason;

    private int point;

    private LocalDateTime date;

}
