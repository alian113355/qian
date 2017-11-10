package com.qian.task.qian.model;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 金十成交量  黄金
 */
public class GlodVolume {

    private String time;
    private int volume;
    private Date timeDate;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Date getTimeDate() throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        return StringUtils.isEmpty(time)?null: df.parse(time);
    }

}
