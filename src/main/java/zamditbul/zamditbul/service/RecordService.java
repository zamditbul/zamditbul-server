package zamditbul.zamditbul.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import zamditbul.zamditbul.data.dao.SleepData;
import zamditbul.zamditbul.data.dto.SleepDataRecord;
import zamditbul.zamditbul.data.dao.User;
import zamditbul.zamditbul.repository.SleepDataRepository;
import zamditbul.zamditbul.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordService {
    private final SleepDataRepository sleepDataRepository;
    private final UserRepository userRepository;

    public void MessageHandle(Message<?> message) {
        JsonToObjectTransformer transformer = new JsonToObjectTransformer(SleepDataRecord.class);
        SleepDataRecord payload = (SleepDataRecord) transformer.transform(message).getPayload();
        log.info(payload.toString());
        Optional<User> user = userRepository.findByUserId(payload.getUser_id());
        if (user.isPresent() && !user.get().getDevice().equals(null)) {
            SleepData sleepData = new SleepData(payload.getDate(), payload.getBreak_count(),
                    payload.getSleep_time(),payload.getWake_time(), payload.getSleep_count(), user.get());
                    sleepDataRepository.save(sleepData);
        }
    }
}
