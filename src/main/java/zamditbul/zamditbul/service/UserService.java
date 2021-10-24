package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.LoginUser;
import zamditbul.zamditbul.data.UserInfo;
import zamditbul.zamditbul.repository.UserInfoRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserInfoRepository userInfoRepository;

    public UserInfo Login(LoginUser user) {
        Optional<UserInfo> getUser = userInfoRepository.findByUserId(user.getId());

        if (getUser.isEmpty()) {
            return null;
        }

        if (user.getPw().equals(getUser.get().getPasswd())) {
            return getUser.get();
        }

        return null;
    }

    public UserInfo Join(LoginUser user) {

        if (user.getPw().isBlank() || user.getId().isBlank()) {
            return null;
        }

        if (userInfoRepository.existsByUserId(user.getId())){
            return null;
        }


        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setPasswd(user.getPw());

        log.info(userInfo.toString());

        return userInfoRepository.save(userInfo);
    }
}
