package zamditbul.zamditbul.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
    LocalDate date;

    @Column
    Integer break_count;

    @Column @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime sleep_time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SleepData(LocalDate date, Integer break_count, LocalTime sleep_time, User user) {
        this.date = date;
        this.break_count = break_count;
        this.sleep_time = sleep_time;
        this.user = user;
    }

}
