package rev.team.AUTHSERVER.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewPwReq {

    private String userId;

    private String password;
}
