package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.dao.Device;
import zamditbul.zamditbul.data.dao.SleepData;
import zamditbul.zamditbul.data.dto.ConnectDevice;
import zamditbul.zamditbul.data.dto.UpdateSetting;
import zamditbul.zamditbul.data.dao.User;
import zamditbul.zamditbul.repository.DeviceRepository;
import zamditbul.zamditbul.repository.SleepDataRepository;
import zamditbul.zamditbul.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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
        Optional<User> user = userRepository.findByUserId(device.getUserId());
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

    public Optional<SleepData> getSleepData(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            return sleepDataRepository.findByUser(user.get());
        }
        return null;
    }

    public Device updateSetting(UpdateSetting update) {
        Optional<User> user = userRepository.findByUserId(update.getUserId());
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
