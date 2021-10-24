package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zamditbul.zamditbul.data.Device;
import zamditbul.zamditbul.data.SleepData;
import zamditbul.zamditbul.data.User;
import zamditbul.zamditbul.service.SettingService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @GetMapping("/user/setting")
    public Device getSetting(@RequestParam String userId) {
        return settingService.getDeviceSetting(userId);
    }

    @PostMapping("/user/setting")
    public HttpStatus newSetting(@RequestParam String userId, @RequestBody Device device) {
        return settingService.updateSleepData(userId, device);
    }

    @GetMapping("/user/record")
    public Optional<SleepData> getSleepData(@RequestParam String userId){
        return settingService.getSleepData(userId);
    }

    @PutMapping("/user/")
    public HttpStatus updateUser(@RequestBody User user) {
        return settingService.updateUser(user);
    }
}
