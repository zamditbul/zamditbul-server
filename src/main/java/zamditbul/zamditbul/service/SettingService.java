package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.Device;
import zamditbul.zamditbul.data.SleepData;
import zamditbul.zamditbul.data.User;
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

    public Optional<SleepData> getSleepData(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            return sleepDataRepository.findByUser(user.get());
        }
        return null;
    }

    public HttpStatus updateSleepData(String userId, Device setting) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isPresent()) {
            Device device = user.get().getDevice();
            device.setColor(setting.getColor());
            device.setSet_sleep(setting.getSet_sleep());
            device.setSet_up(setting.getSet_up());
            device.setDoNotDisturb(setting.isDoNotDisturb());

            deviceRepository.save(device);

            return HttpStatus.OK;
        }
        return HttpStatus.UNAUTHORIZED;
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
