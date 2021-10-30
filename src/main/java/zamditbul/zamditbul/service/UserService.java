package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.Device;
import zamditbul.zamditbul.data.LoginUser;
import zamditbul.zamditbul.data.User;
import zamditbul.zamditbul.repository.DeviceRepository;
import zamditbul.zamditbul.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public User Login(LoginUser user) {
        Optional<User> getUser = userRepository.findByUserId(user.getUser_id());

        if (getUser.isEmpty()) {
            return null;
        }

        if (user.getUser_pw().equals(getUser.get().getPasswd())) {
            return getUser.get();
        }

        return null;
    }

    public User Join(LoginUser user) {

        if (user.getUser_pw().isBlank() || user.getUser_id().isBlank()) {
            return null;
        }

        if (userRepository.existsByUserId(user.getUser_id()) || deviceRepository.existsByUserId(user.getUser_id())){
            return null;
        }



        Device device = new Device();
        device.setSerialNum("NOT_CONNECTED");
        device.setUserId(user.getUser_id());

        User userInfo = new User();
        userInfo.setUserId(user.getUser_id());
        userInfo.setPasswd(user.getUser_pw());
        userInfo.setDevice(deviceRepository.save(device));
        log.info(userInfo.toString());

        return userRepository.save(userInfo);
    }

    public boolean isIdExists(String userId) {

        if (userRepository.existsByUserId(userId)) {
            return true;
        }

        return false;
    }
}
