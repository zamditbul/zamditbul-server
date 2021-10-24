package zamditbul.zamditbul.data;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@ToString
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String userId;

    @Column
    private String passwd;

    @OneToOne
    @JoinColumn(name = "serialNum")
    private Device device;

    public void setUserId(String userId ) {
        this.userId = userId;
    }

    public void setPasswd(String passwd){
        this.passwd = passwd;
    }
}
