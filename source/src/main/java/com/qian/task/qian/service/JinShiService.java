package com.qian.task.qian.service;

import com.qian.task.qian.entity.GlodEntity;
import com.qian.task.qian.mapper.GlodVolumeMapper;
import com.qian.task.qian.model.GlodVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JinShiService {

    @Value("${jinshi.url}")
    String volumesUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private GlodVolumeMapper glodVolumeMapper;

    public void volumesTask(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now=new Date();
        try {
            List<GlodEntity> data=glodVolumeMapper.getByBimeDate(df.parse(dateFormat.format(now)+" 00:00:00"));
            List<GlodVolume> apiData=getVolumes();
            List<GlodEntity> add=new ArrayList<>();
            List<GlodEntity> up=new ArrayList<>();
            for(GlodVolume gv:apiData){
                Optional<GlodEntity> op=data.parallelStream().filter(s->s.getTime().equals(gv.getTime())).findFirst();
                if(op.isPresent()){
                   //修改
                    GlodEntity og=op.get();
                    if(og.getVolume().intValue()!=gv.getVolume()){
                        og.setVolume((long)gv.getVolume());
                        up.add(og);
                    }
                }else{
                    //新增
                    GlodEntity glodEntity=new GlodEntity();
                    glodEntity.setCreateTime(new Date());
                    glodEntity.setLastTime(glodEntity.getCreateTime());
                    glodEntity.setTime(gv.getTime());
                    glodEntity.setTimeDate(gv.getTimeDate());
                    glodEntity.setVolume(((long)gv.getVolume()));
                    add.add(glodEntity);
                }
            }
            if(!add.isEmpty()){
                for(GlodEntity ge:add)
                    glodVolumeMapper.insert(ge);
            }
            if(!up.isEmpty())
                for(GlodEntity ge:up)
                    glodVolumeMapper.updateVolume(ge);

            System.out.println("add:"+add.size()+",up:"+up.size());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<GlodVolume> getVolumes(){
        List<GlodVolume> list=new ArrayList<>();
        try {
            ArrayList<?> c=new ArrayList<GlodVolume>();
            ArrayList<?> v= restTemplate.exchange(new URI(volumesUrl), HttpMethod.GET, HttpEntity.EMPTY, c.getClass()).getBody();
            for (Object aV : v) {
                Map<String, String> map = (Map<String, String>) aV;
                GlodVolume gv = new GlodVolume();
                gv.setVolume(Integer.parseInt(map.get("volume")));
                gv.setTime(map.get("time"));
                list.add(gv);
            }
//            System.out.println(v);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

}
