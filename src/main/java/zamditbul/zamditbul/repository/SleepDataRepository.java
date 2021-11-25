package zamditbul.zamditbul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zamditbul.zamditbul.data.dao.SleepData;
import zamditbul.zamditbul.data.dao.User;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaAuditing
public interface SleepDataRepository extends JpaRepository<SleepData, Integer> {
    Optional<SleepData> findByUser(User user);

    List<SleepData> findByUser_IdOrderByDateDesc(Integer userId);

    @Query(value = "SELECT AVG(d.sleep_time) FROM SleepData d WHERE d.user.id = :userId")
    Integer getAVGSleepTime(@Param("userId") Integer userId);

    @Query(value = "SELECT AVG(d.break_count) FROM SleepData d WHERE d.user.id = :userId")
    Integer getAGVBreakCount(@Param("userId") Integer userId);

    @Query(value = "SELECT avg(d.wake_time) from SleepData d WHERE d.user.id = :userId")
    Integer getAGVWakeTime(@Param("userId") Integer userId);

    @Query(value = "SELECT avg(d.sleep_count) from SleepData d WHERE d.user.id = :userId")
    Integer getAGVSleepCount(@Param("userId") Integer userId);
}
