package zamditbul.zamditbul.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityUser extends User {
    private static final String ROLE_PREFIX = "ROLE_";

    private zamditbul.zamditbul.data.User user;

    public SecurityUser(zamditbul.zamditbul.data.User user){
        super(String.valueOf(user.getUserId()), user.getPasswd(),makeGrantedAuthority("user"));
    }
    private static List<GrantedAuthority> makeGrantedAuthority(String userType){

        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + userType));

        return list;

    }
}
