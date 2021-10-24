package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import zamditbul.zamditbul.data.LoginUser;
import zamditbul.zamditbul.data.User;
import zamditbul.zamditbul.service.UserService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/auth/user")
    public User Login(@RequestBody LoginUser user) {
        return userService.Login(user);
    }

    @PostMapping(value = "/auth/user/new")
    public User Join(@RequestBody LoginUser user) {
        log.info(user.toString());

        return userService.Join(user);
    }

    @GetMapping("/auth/user/new")
    public boolean isIdExists(@RequestParam String userId){
        return userService.isIdExists(userId);
    }


}
