package zamditbul.zamditbul.data.dto;

import lombok.Getter;
import lombok.ToString;
import zamditbul.zamditbul.data.dao.Device;

@Getter
@ToString
public class UpdateSetting {
    String user_id;
    Device device;
}