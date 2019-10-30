package net.anumbrella.rabbitmq.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author Anumbrella
 * @author mygodzj
 */
@Slf4j
@Controller
public class ReceiveController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/client")
    public void all(String message) {
        log.info("*** 来自客户端的消息 ***:" + message);
        //simpMessagingTemplate.convertAndSend("/topic/notice", JSON.toJSONString("这是通知消息！！"));
        simpMessagingTemplate.convertAndSend("/topic/notice", "广播：这是你发送的消息"+message);
    }
}
