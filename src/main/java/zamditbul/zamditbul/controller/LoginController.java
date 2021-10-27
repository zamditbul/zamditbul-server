package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zamditbul.zamditbul.data.LoginUser;
import zamditbul.zamditbul.data.User;
import zamditbul.zamditbul.service.UserService;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/auth/user")
    public HttpStatus Login(@RequestBody LoginUser user) {

        User login = userService.Login(user);

        if (login == null) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }


    @DeleteMapping("/auth/user")
    public HttpStatus Logout() {
        //추후 코드 수정

        return HttpStatus.OK;
    }

    @PostMapping(value = "/auth/user/new")
    public User Join(@RequestBody LoginUser user) {
        return userService.Join(user);
    }

    @GetMapping("/auth/user/new")
    public boolean isIdExists(@RequestParam(value = "userId", required = false) String userId){
        return userService.isIdExists(userId);
    }



}
