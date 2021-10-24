package zamditbul.zamditbul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zamditbul.zamditbul.data.SleepData;
import zamditbul.zamditbul.data.User;

import java.util.Optional;

public interface SleepDataRepository extends JpaRepository<SleepData, Integer> {
    Optional<SleepData> findByUser(User user);
}
