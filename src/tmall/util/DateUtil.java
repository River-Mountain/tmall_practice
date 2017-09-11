package tmall.util;

/**
 * Created by mountain on 2017/8/31.
 * 日期格式转换
 */
public class DateUtil {
    public static java.sql.Timestamp d2t (java.util.Date d) {
        if(d == null)
            return null;
        // 返回的必须是个实例，而不是类
        return new java.sql.Timestamp(d.getTime());
    }

    public static java.util.Date t2d (java.sql.Timestamp t) {
        if(t == null)
            return null;
        return new java.util.Date(t.getTime());
    }
}
