package com.javatechie.redis.publisher;

import com.javatechie.redis.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@Slf4j
public class Publisher {
    @Autowired
    private RedisTemplate template;
    @Autowired
    private ChannelTopic topic;

    @PostMapping("/publish")
    public String publish(@RequestBody Student student) {
        log.info("Message publishing on topic: {}", topic.getTopic());
        template.convertAndSend(topic.getTopic(), student.toString());
        return "Event Published !!";
    }
}
