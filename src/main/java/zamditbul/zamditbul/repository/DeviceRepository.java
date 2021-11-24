package zamditbul.zamditbul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zamditbul.zamditbul.data.dao.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
