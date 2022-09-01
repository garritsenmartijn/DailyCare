package garritsen.vxpush.job;

import garritsen.vxpush.utils.Pusher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Garritsen
 * @Version 1.0.0
 * @Description xxx
 * Created at 2022/9/1 09:44
 */
@Component
public class ScheduleJob {

    //要推送的用户openid
    private static final String OPEN_ID = "xxxxxxxxxxxxxxxx";

    @Scheduled(cron = "0 30 7 * * ?")
    public void goodMorning(){
        Pusher.push(OPEN_ID);
    }
}
