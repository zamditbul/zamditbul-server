package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zamditbul.zamditbul.data.LoginUser;
import zamditbul.zamditbul.data.UserInfo;
import zamditbul.zamditbul.service.UserService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/auth/user")
    public UserInfo Login(@RequestBody LoginUser user) {
        return userService.Login(user);
    }

    @PostMapping(value = "/auth/user/new")
    public UserInfo Join(@RequestBody LoginUser user) {
        log.info(user.toString());

        return userService.Join(user);
    }
}
