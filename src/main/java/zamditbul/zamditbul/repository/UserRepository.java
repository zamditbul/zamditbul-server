package zamditbul.zamditbul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zamditbul.zamditbul.data.Device;
import zamditbul.zamditbul.data.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUserId(String userId);

    public Boolean existsByUserId(String userId);

}
