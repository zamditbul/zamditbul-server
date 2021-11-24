package zamditbul.zamditbul.data.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Device {

    @Id @GeneratedValue
    Integer deviceId;

    @Column
    String serialNum;

    @Column
    String color;

    @Column
    LocalTime sleep;

    @Column
    LocalTime wake_up;

    @Column
    Boolean doNotDisturb;

}
