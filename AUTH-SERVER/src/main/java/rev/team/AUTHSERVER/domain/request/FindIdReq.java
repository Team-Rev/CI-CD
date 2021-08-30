package rev.team.AUTHSERVER.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FindIdReq {

    private String name;

    private String phone;
}
