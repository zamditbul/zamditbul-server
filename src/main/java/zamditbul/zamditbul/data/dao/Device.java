package zamditbul.zamditbul.data.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
