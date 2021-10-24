package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.LoginUser;
import zamditbul.zamditbul.data.User;
import zamditbul.zamditbul.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

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

        if (userRepository.existsByUserId(user.getUser_id())){
            return null;
        }


        User userInfo = new User();
        userInfo.setUserId(user.getUser_id());
        userInfo.setPasswd(user.getUser_pw());

        log.info(userInfo.toString());

        return userRepository.save(userInfo);
    }

    public boolean isIdExists(String userId) {
        Optional<User> exists = userRepository.findByUserId(userId);

        if (exists.isPresent()) {
            return true;
        }

        return false;
    }
}
