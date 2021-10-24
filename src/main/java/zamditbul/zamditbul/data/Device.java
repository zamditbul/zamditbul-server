package zamditbul.zamditbul.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

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
    Time set_sleep;

    @Column
    Time set_up;

    @Column
    boolean doNotDisturb;
}
