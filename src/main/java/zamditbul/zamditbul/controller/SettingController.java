package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zamditbul.zamditbul.data.dao.SleepData;
import zamditbul.zamditbul.data.dto.ConnectDevice;
import zamditbul.zamditbul.data.dto.UpdateSetting;
import zamditbul.zamditbul.data.dao.User;
import zamditbul.zamditbul.service.SettingService;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class SettingController {

    private final SettingService settingService;

    @PostMapping("/user/device")
    public HttpStatus newDevice(@RequestBody ConnectDevice connect) {
        return settingService.newDevice(connect);
    }

    @GetMapping("/user/setting")
    public Object getSetting(@RequestParam(value = "userId", required = false) String userId) {
        return settingService.getDeviceSetting(userId);
    }

    @PostMapping("/user/setting")
    public HttpStatus newSetting(@RequestBody UpdateSetting device) throws MqttException {

        if (settingService.updateSetting(device).equals(null))
            return HttpStatus.UNAUTHORIZED;
        else{
            MqttMessage message = new MqttMessage(device.toString().getBytes(StandardCharsets.UTF_8));
            String clientUrl = "tcp://localhost:1883";
            MqttClient mqttClient = new MqttClient(clientUrl, MqttAsyncClient.generateClientId());
            mqttClient.connect();

            String uri = "setting/" + device.getDevice().getSerialNum();

            mqttClient.publish(uri, message);

            return HttpStatus.OK;
        }
    }


    @GetMapping("/user/record")
    public SleepData getSleepData(@RequestParam String userId) {
        return settingService.getSleepData(userId).get();
    }

    @PutMapping("/user/info")
    public HttpStatus updateUser(@RequestBody User userInfo) {
        return settingService.updateUser(userInfo);
    }
}
