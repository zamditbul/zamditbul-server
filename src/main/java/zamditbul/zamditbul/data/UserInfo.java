package zamditbul.zamditbul.data;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@ToString
public class UserInfo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String passwd;

    public void setUserId(String userId ) {
        this.userId = userId;
    }

    public void setPasswd(String passwd){
        this.passwd = passwd;
    }
}
