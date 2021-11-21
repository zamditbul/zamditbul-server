package zamditbul.zamditbul.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SleepDataInfo {
    private List<SleepData> sleepData;
    private Integer avg_sleep;
    private Integer avg_break;
}
