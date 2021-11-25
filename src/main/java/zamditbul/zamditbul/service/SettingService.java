package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.dao.Device;
import zamditbul.zamditbul.data.dto.ConnectDevice;
import zamditbul.zamditbul.data.dto.SleepDataInfo;
import zamditbul.zamditbul.data.dto.UpdateSetting;
import zamditbul.zamditbul.data.dao.User;
import zamditbul.zamditbul.repository.DeviceRepository;
import zamditbul.zamditbul.repository.SleepDataRepository;
import zamditbul.zamditbul.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SettingService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final SleepDataRepository sleepDataRepository;

    public Device getDeviceSetting(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            return user.get().getDevice();
        }
        else return null;
    }

    public HttpStatus newDevice(ConnectDevice device) {
        Optional<User> user = userRepository.findByUserId(device.getUser_id());
        if (user.isEmpty()) {
            return HttpStatus.UNAUTHORIZED;
        }
        User saved = user.get();

        Device savedDevice = saved.getDevice();
        savedDevice.setSerialNum(device.getSerialNum());
        saved.setDevice(savedDevice);

        userRepository.save(saved);
        deviceRepository.save(savedDevice);

        return HttpStatus.OK;

    }

    public SleepDataInfo getSleepData(String userId) {
        Optional<User> saved = userRepository.findByUserId(userId);
        if (saved.isEmpty()) {
            return null;
        }
        User user = saved.get();
        Integer id = user.getId();

        SleepDataInfo info = new SleepDataInfo();

        Integer average_sleep = sleepDataRepository.getAVGSleepTime(id);
        LocalTime sleep = LocalTime.of(average_sleep / 10000, (average_sleep % 10000) / 100);

        Integer avg_break = sleepDataRepository.getAGVBreakCount(id);

        Integer agv_wake = sleepDataRepository.getAGVWakeTime(id);
        LocalTime wake = LocalTime.of(agv_wake / 10000, (average_sleep % 10000) / 100);

        Integer agv_sleep_time = sleepDataRepository.getAGVSleepCount(id);
        LocalTime sleep_time = LocalTime.of(agv_sleep_time / 10000, (average_sleep % 10000) / 100);

        info.setSleepData(sleepDataRepository.findByUser_IdOrderByDateDesc(id));
        info.setAvg_sleep(sleep_time);
        info.setAvg_break(avg_break);
        info.setAvg_wake(wake);
        info.setAvg_sleep_time(sleep);
        return info;
    }

    public Device updateSetting(UpdateSetting update) {
        Optional<User> user = userRepository.findByUserId(update.getUser_id());
        if (user.isPresent()) {
            Device device = user.get().getDevice();
            Device setting = update.getDevice();
            device.setColor(setting.getColor());
            device.setSleep(update.getDevice().getSleep());
            device.setWake_up(update.getDevice().getWake_up());
            device.setDoNotDisturb(setting.getDoNotDisturb());
            return deviceRepository.save(device);

        }
        else return null;
    }

    public HttpStatus updateUser(User user) {
        Optional<User> byUserId = userRepository.findByUserId(user.getUserId());
        if (byUserId.isPresent()) {
            User update_user = byUserId.get();
            update_user.setPasswd(user.getPasswd());

            userRepository.save(update_user);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

}
