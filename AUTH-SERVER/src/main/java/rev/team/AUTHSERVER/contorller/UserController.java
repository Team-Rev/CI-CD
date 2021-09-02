package rev.team.AUTHSERVER.contorller;

import org.springframework.web.bind.annotation.*;
import rev.team.AUTHSERVER.domain.RevUser;
import rev.team.AUTHSERVER.domain.request.FindIdReq;
import rev.team.AUTHSERVER.domain.request.FindPwReq;
import rev.team.AUTHSERVER.domain.request.NewPwReq;
import rev.team.AUTHSERVER.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public RevUser getUser(@RequestParam("username") String username){
        return userService.findUser(username).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody RevUser user){
        return userService.save(user);
    }

    @GetMapping("/nickname")
    public String getNickname(@RequestParam String username){
        return userService.findUser(username).orElseThrow(RuntimeException::new).getNickname();
    }

    @GetMapping("/userPoint")
    public Long getPoint(@RequestParam String username) {
        return userService.findUser(username).orElseThrow(RuntimeException::new).getPoint();
    }

    @GetMapping("/findId")
    public String findId(@RequestBody FindIdReq findIdReq) {
        return userService.findId(findIdReq).orElseThrow(RuntimeException::new).getUsername();
    }

    @GetMapping("/findPw")
    public String findPw(@RequestBody FindPwReq findPwReq) {
        return userService.findPw(findPwReq).orElseThrow(RuntimeException::new).getUsername();
    }

    @PostMapping("/changeNewPw")
    public String changeNewPw(@RequestBody NewPwReq newPwReq) { return userService.changeNewPw(newPwReq); }
}