package garritsen.vxpush.utils;

import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;

/**
 * @Author Garritsen
 * @Version 1.0.0
 * @Description xxx
 * Created at 2022/9/1 09:41
 */
public class WeatherForecast {

    //百度天气api
    private static String ak = "bHk3UXAfl8QhXQrrCQIfIqRQjrVs69st";
    private static String district_id = "120000";

    //聚合api
    private static String key = "23e2981a9cfe3943d7bc133ad4e0a39e";

    public static JSONObject getTjWether() {
        String result = null;
        JSONObject today = new JSONObject();
        try {
            String city = URLEncoder.encode("天津", "UTF-8");
            result = HttpUtil.getUrl("http://apis.juhe.cn/simpleWeather/query?city="+city+"&key="+key);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getString("reason").equals("查询成功!")) {

                today = jsonObject.getJSONObject("result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return today;
    }
}
