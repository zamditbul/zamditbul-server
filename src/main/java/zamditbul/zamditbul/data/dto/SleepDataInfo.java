package zamditbul.zamditbul.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import zamditbul.zamditbul.data.dao.SleepData;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class SleepDataInfo {
    private List<SleepData> sleepData;
    private LocalTime avg_sleep;
    private LocalTime avg_break;
}
