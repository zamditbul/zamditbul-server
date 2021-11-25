package zamditbul.zamditbul.data.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SleepData {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer sleepId;

    @Column
    LocalDate date;

    @Column
    Integer break_count;

    @Column @DateTimeFormat(pattern = "HH:mm")
    LocalTime sleep_time;

    @Column @DateTimeFormat(pattern = "HH:mm")
    LocalTime wake_time;

    @Column @DateTimeFormat(pattern = "HH:mm")
    LocalTime sleep_count;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public SleepData(LocalDate date, Integer break_count, LocalTime sleep_time, LocalTime wake_time, LocalTime sleep_count, User user) {
        this.date = date;
        this.break_count = break_count;
        this.sleep_time = sleep_time;
        this.wake_time = wake_time;
        this.sleep_count = sleep_count;
        this.user = user;
    }

}
