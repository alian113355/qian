package com.qian.task.qian.scheduled;

import com.qian.task.qian.service.JinShiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JinShiTasks {
    private static final Logger log = LoggerFactory.getLogger(JinShiTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    JinShiService jinShiService;

    @Scheduled(cron = "0/50 * * * * ?")
    public void reportCurrentTime() {
        log.info("reportCurrentTime begin {}", dateFormat.format(new Date()));
        jinShiService.volumesTask();
        log.info("reportCurrentTime end {}", dateFormat.format(new Date()));
    }
}
