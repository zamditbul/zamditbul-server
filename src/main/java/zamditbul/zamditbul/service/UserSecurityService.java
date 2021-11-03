package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.SecurityUser;
import zamditbul.zamditbul.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        return
                userRepo.findByUserId(userId)
                        .filter(m -> m != null)
                        .map(m -> new SecurityUser(m)).get();
    }
}