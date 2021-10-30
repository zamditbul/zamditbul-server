package zamditbul.zamditbul.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "device", referencedColumnName = "userId")
    @JsonIgnore
    private Device device;

    public void setUserId(String userId ) {
        this.userId = userId;
    }

    public void setPasswd(String passwd){
        this.passwd = passwd;
    }

    public void setDevice(Device device) {this.device = device;}
}
