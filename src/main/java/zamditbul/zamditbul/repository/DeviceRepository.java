package zamditbul.zamditbul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zamditbul.zamditbul.data.Device;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    public Boolean existsByUserId(String userId);
}
