package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.config.JwtTokenProvider;
import zamditbul.zamditbul.data.dao.Device;
import zamditbul.zamditbul.data.dao.User;
import zamditbul.zamditbul.data.dto.LoginUser;
import zamditbul.zamditbul.repository.DeviceRepository;
import zamditbul.zamditbul.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class UserService {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final JwtTokenProvider jwtTokenProvider;
    public User Login(LoginUser user) {
        Optional<User> getUser = userRepository.findByUserId(user.getUser_id());

        if (getUser.isEmpty()) {
            return null;
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(user.getUser_pw(), getUser.get().getPasswd())){
            getUser.get().setToken(jwtTokenProvider.createToken(String.valueOf(user.getUser_id()), user.getUser_pw()));
            return getUser.get();

        }

        return null;
    }

    public User Join(LoginUser user) {

        if (user.getUser_pw().isBlank() || user.getUser_id().isBlank()) {
            return null;
        }

        if (userRepository.existsByUserId(user.getUser_id())){
            return null;
        }

        String encryptedPassword = new BCryptPasswordEncoder(4).encode(user.getUser_pw());
        Device device = new Device();
        device.setSerialNum("NOT_CONNECTED");

        User userInfo = new User();
        userInfo.setUserId(user.getUser_id());
        userInfo.setPasswd(encryptedPassword);
        userInfo.setDevice(deviceRepository.save(device));
        log.info(userInfo.toString());

        return userRepository.save(userInfo);
    }
    public boolean logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return true;
    }

    public boolean isIdExists(String userId) {

        if (userRepository.existsByUserId(userId)) {
            return true;
        }

        return false;
    }
}
