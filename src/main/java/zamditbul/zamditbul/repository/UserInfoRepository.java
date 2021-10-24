package zamditbul.zamditbul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zamditbul.zamditbul.data.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    public Optional<UserInfo> findByUserId(String userId);

    public Boolean existsByUserId(String userId);
}
