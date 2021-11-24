package zamditbul.zamditbul.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class SleepDataRecord {

    Integer userId;
    LocalDate date;
    Integer break_count;
    @DateTimeFormat(pattern = "HH:mm:ss")
    LocalTime sleep_time;

}
