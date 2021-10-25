package zamditbul.zamditbul.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Device {
    @Id @Column
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
    boolean doNotDisturb;
}
