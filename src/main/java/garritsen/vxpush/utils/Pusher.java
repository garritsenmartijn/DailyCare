package garritsen.vxpush.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import garritsen.vxpush.model.FutureWeather;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *@ClassName Pusher
 *@Description TODO
 *@Author Garritsen
 *@Date 2022/8/2 16:03
 */
public class Pusher {
    /**
     * 测试号的appId和secret
     */
    private static final String APP_ID = "xxxxxxxxxxxxxxxx";
    private static final String SECRET = "xxxxxxxxxxxxxxxx";
    /**
     * 模版id
     */
    private static final String TEMPLATE_ID = "xxxxxxxxxxxxxxxx";

    public static void push(String openId){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(APP_ID);
        wxStorage.setSecret(SECRET);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(TEMPLATE_ID)
                //.url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        //填写变量信息，比如天气之类的
        JSONObject weather = WeatherForecast.getTjWether();

        JSONObject realtimeWeather = weather.getJSONObject("realtime");
        JSONArray futureWeather = weather.getJSONArray("future");

        String windDirection = realtimeWeather.getString("direct");
        String windPower = realtimeWeather.getString("power");
        String nowTemperature = realtimeWeather.getString("temperature");

        List<FutureWeather> futureWeathers = JSONArray.parseArray(futureWeather.toJSONString(), FutureWeather.class);
        FutureWeather todayWeather = futureWeathers.get(0);
        String temperature = todayWeather.getTemperature();
        String[] split = temperature.split("/");

        //接口
        templateMessage.addData(new WxMpTemplateData("riqi",todayWeather.getDate() + "  "+ getWeekOfDate(new Date()),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",todayWeather.getWeather(),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low",split[0] +"℃" + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("high",split[1]+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi",SweetWords.getSweetWords(),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai",MemorialDay.getLianAi()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri",MemorialDay.getShengRi()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("jinju",SweetWords.getJinJu()+"","#C71585"));
        //templateMessage.addData(new WxMpTemplateData("jiehun",JiNianRi.getJieHun()+""));
//        templateMessage.addData(new WxMpTemplateData("linzhen",JiNianRi.getLinZhen()+"","#FF6347"));
        String beizhu = "";
        if(MemorialDay.getJieHun() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        if(MemorialDay.getLianAi() % 365 == 0){
            beizhu = "今天是恋爱纪念日！";
        }
        if(MemorialDay.getLinZhen() % 365 == 0){
            beizhu = "今天是结婚纪念日！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));


        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "尊贵星期日", "冠军星期一", "摸鱼星期二", "希望星期三", "疯狂星期四", "至尊星期五", "尊贵星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }
}
