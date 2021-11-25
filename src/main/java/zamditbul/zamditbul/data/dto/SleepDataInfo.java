package zamditbul.zamditbul.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zamditbul.zamditbul.data.dao.SleepData;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SleepDataInfo {
    private List<SleepData> sleepData;
    private LocalTime avg_sleep;
    private Integer avg_break;
    private LocalTime avg_sleep_time;
    private LocalTime avg_wake;
}
