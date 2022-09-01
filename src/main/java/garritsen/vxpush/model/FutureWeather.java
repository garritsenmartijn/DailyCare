package garritsen.vxpush.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author Garritsen
 * @Version 1.0.0
 * @Description xxx
 * Created at 2022/8/31 17:22
 */
@Data
public class FutureWeather {
    String date;
    JSONObject wid;
    String temperature;
    String weather;
    String direct;
}
