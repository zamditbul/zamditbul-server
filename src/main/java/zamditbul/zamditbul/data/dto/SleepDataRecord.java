package zamditbul.zamditbul.data.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class SleepDataRecord {

    String user_id;
    LocalDate date;
    Integer break_count;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime sleep_time;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime wake_time;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime sleep_count;
}
