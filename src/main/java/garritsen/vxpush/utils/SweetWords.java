package garritsen.vxpush.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Garritsen
 * @Version 1.0.0
 * @Description xxx
 * Created at 2022/9/1 09:37
 */
public class SweetWords {

    /**
     * 对接天行数据的土味情话api
     */
    private static final String KEY = "xxx";
    private static final String URL = "http://api.tianapi.com/saylove/index?key=";
    private static List<String> jinJuList = new ArrayList<>();
    private static final String NAME = "老婆";

    public static String getSweetWords() {
        //默认彩虹屁
        String str = "鱼在水里，你在我心里";
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.getUrl(URL+KEY).replace("XXX", NAME));
            if (jsonObject.getIntValue("code") == 200) {
                str = jsonObject.getJSONArray("newslist").getJSONObject(0).getString("content");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 载入金句库
     */
    static {
        InputStream inputStream = SweetWords.class.getClassLoader().getResourceAsStream("jinju.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String str = "";
            String temp = "";
            while ((temp = br.readLine()) != null) {
                if (!StringUtils.isEmpty(temp)) {
                    str = str + "\r\n" + temp;
                } else {
                    jinJuList.add(str);
                    str = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJinJu() {
        Random random = new Random();
        return jinJuList.get(random.nextInt(jinJuList.size()));
    }
}
