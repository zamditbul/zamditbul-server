package zamditbul.zamditbul.data.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String userId;

    @Column
    private String passwd;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "SerialNum")
    @JsonIgnore
    private Device device;

    private String token;

    public void setUserId(String userId ) {
        this.userId = userId;
    }

    public void setPasswd(String passwd){
        this.passwd = passwd;
    }

    public void setDevice(Device device) {this.device = device;}
}
