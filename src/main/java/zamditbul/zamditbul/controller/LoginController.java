package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zamditbul.zamditbul.data.dto.LoginUser;
import zamditbul.zamditbul.data.dao.User;
import zamditbul.zamditbul.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/auth/user")
    public User Login(@RequestBody LoginUser user) {
        return userService.Login(user);
    }


    @GetMapping("/auth/user")
    public HttpStatus Logout(HttpServletRequest request) {
        if(userService.logout(request)){
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
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
