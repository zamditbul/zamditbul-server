package zamditbul.zamditbul.data.dto;

import lombok.Getter;
import lombok.ToString;
import zamditbul.zamditbul.data.dao.Device;

@Getter
@ToString
public class LoginUser {
    private String user_id;
    private String user_pw;
}
