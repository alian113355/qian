package com.qian.task.qian.mapper;

import com.qian.task.qian.entity.GlodEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GlodVolumeMapper {

    @Insert("INSERT INTO `glod_volume` ( `time`, `volume`, `time_date`, `create_time`, `last_time`) VALUES ( #{time}, #{volume}, #{timeDate}, #{createTime}, #{lastTime});")
    void insert(GlodEntity glod);

    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "time", column = "time"),
            @Result(property = "timeDate", column = "time_date"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "lastTime", column = "last_time"),
    })
    @Select("select * from `glod_volume` where time_date>= #{time}")
    List<GlodEntity> getByBimeDate(Date time);

    @Update("update `glod_volume` set volume=#{volume}  where id=#{id}")
    void updateVolume(GlodEntity glod);
}
