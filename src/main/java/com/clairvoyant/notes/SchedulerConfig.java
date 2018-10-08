package com.clairvoyant.notes;

import com.clairvoyant.notes.vo.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedDelay = 3000)
    public void sendAdhocMessages(){
        simpMessagingTemplate.convertAndSend("/topic/user",new UserResponse("Fixed Delay Scheduler"));
    }
}
