package cn.kgc.smbms.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * @author:liao
 * @Description:TODO
 * @date:2020/8/10
 */
public class DateMapper extends ObjectMapper {
    public DateMapper() {
        //关闭原先spring mvc自带的日期格式
        this.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        //重新定义自己想要的日期输出格式
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }
}
