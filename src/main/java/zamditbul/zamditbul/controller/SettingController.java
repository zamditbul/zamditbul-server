package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zamditbul.zamditbul.data.Device;
import zamditbul.zamditbul.data.LoginUser;
import zamditbul.zamditbul.data.User;
import zamditbul.zamditbul.service.SettingService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class SettingController {

    private final SettingService settingService;

    @GetMapping("/user/setting")
    public Object getSetting(@RequestParam String userId) {
        return settingService.getDeviceSetting(userId);
    }

    @PostMapping("/user/setting")
    public HttpStatus newSetting(@RequestParam String userId, @RequestBody Device device) {

        return settingService.updateSleepData(userId, device);

    }


    @GetMapping("/user/record")
    public Object getSleepData(@RequestParam String userId) {
        return settingService.getSleepData(userId);
    }

    @PutMapping("/user/info")
    public HttpStatus updateUser(@RequestBody User userInfo) {
        return settingService.updateUser(userInfo);
    }
}
