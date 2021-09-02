package rev.team.AUTHSERVER.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus
public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("user not found");
    }
}
