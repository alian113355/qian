package com.qian.task.qian.entity;

import java.io.Serializable;
import java.util.Date;

public class GlodEntity implements Serializable {
    private Long id;
    private String time;
    private Long volume;
    private Date timeDate;
    private Date createTime;
    private Date lastTime;

    public GlodEntity(){}

    public GlodEntity(Long id, String time, Long volume, Date timeDate, Date createTime, Date lastTime) {
        this.id = id;
        this.time = time;
        this.volume = volume;
        this.timeDate = timeDate;
        this.createTime = createTime;
        this.lastTime = lastTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Date getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(Date timeDate) {
        this.timeDate = timeDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
