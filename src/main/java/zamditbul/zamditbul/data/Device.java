package zamditbul.zamditbul.data;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Device {

    @Id
    @Column
    String userId;

    @Column
    String serialNum;

    @Column
    String color;

    @Column
    Integer sleep_hour;

    @Column
    Integer sleep_min;

    @Column
    Integer wake_hour;

    @Column
    Integer wake_min;

    @Column
    Boolean doNotDisturb;

}
