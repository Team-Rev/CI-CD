package rev.team.AUTHSERVER.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FindPwReq {

    private String username;

    private String name;

    private String phone;
}
