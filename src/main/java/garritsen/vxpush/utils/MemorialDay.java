package garritsen.vxpush.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author Garritsen
 * @Version 1.0.0
 * @Description xxx
 * Created at 2022/9/1 09:39
 */
public class MemorialDay {

    /**
     * 恋爱
     */
    static String lianAi = "9999-99-99";
    /**
     * 领证
     */
    static String linZheng = "9999-99-99";
    /**
     * 结婚
     */
    static String jieHun = "9999-99-99";
    /**
     * 生日
     */
    static String shengRi = "9999-99-99";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 距离date还有多少天
     * @param date
     * @return
     */
    public static int before(String date) {
        int day = 0;
        try {
            long time = simpleDateFormat.parse(date).getTime() - System.currentTimeMillis();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


    /**
     * 已经过去date多少天
     * @param date
     * @return
     */
    public static int after(String date) {
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L)+1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static int getJieHun() {
        return before(jieHun);
    }

    public static int getLinZhen() {
        return before(linZheng);
    }

    public static int getLianAi() {
        return after(lianAi);
    }

    public static int getShengRi(){
        return after(shengRi);
    }
}
