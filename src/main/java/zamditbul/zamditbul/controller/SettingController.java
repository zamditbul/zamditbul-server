package zamditbul.zamditbul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zamditbul.zamditbul.data.dao.User;
import zamditbul.zamditbul.data.dto.ConnectDevice;
import zamditbul.zamditbul.data.dto.SleepDataInfo;
import zamditbul.zamditbul.data.dto.UpdateSetting;
import zamditbul.zamditbul.service.SettingService;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class SettingController {

    private final SettingService settingService;

    @Value("${mqtt.client.url}")
    private final String clientUrl;

    @PostMapping("/user/device")
    public HttpStatus newDevice(@RequestBody ConnectDevice connect) throws MqttException {

        HttpStatus httpStatus = settingService.newDevice(connect);

        if (httpStatus.is4xxClientError()) {
            return HttpStatus.UNAUTHORIZED;
        }
        MqttMessage message = new MqttMessage(connect.getUser_id().getBytes(StandardCharsets.UTF_8));
        MqttClient mqttClient = new MqttClient(clientUrl, MqttAsyncClient.generateClientId());
        mqttClient.connect();

        String uri = "setting/" + connect.getSerialNum();

        mqttClient.publish(uri, message);

        mqttClient.disconnect();
        return HttpStatus.OK;
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
            MqttClient mqttClient = new MqttClient(clientUrl, MqttAsyncClient.generateClientId());
            mqttClient.connect();

            String uri = "setting/" + device.getDevice().getSerialNum();

            mqttClient.publish(uri, message);

            mqttClient.disconnect();

            return HttpStatus.OK;
        }
    }


    @GetMapping("/user/record")
    public SleepDataInfo getSleepData(@RequestParam String userId) {

        SleepDataInfo sleepData = settingService.getSleepData(userId);
        return sleepData;
    }

    @PutMapping("/user/info")
    public HttpStatus updateUser(@RequestBody User userInfo) {
        return settingService.updateUser(userInfo);
    }
}
