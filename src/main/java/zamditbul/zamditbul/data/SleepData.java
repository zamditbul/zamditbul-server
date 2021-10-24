package zamditbul.zamditbul.data;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
public class SleepData {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer sleepId;

    @Column
    Date date;

    @Column
    Integer break_count;

    @Column
    Integer sleep_time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
