
package com.alfred.zuo.test.websocket.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anumbrella
 * @author mygodzj
 */
@Slf4j
@RestController
@RequestMapping("/websocket")
public class SendController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    /**
     * 通知消息
     */
    @GetMapping("/notice")
    public void notice() {
        messagingTemplate.convertAndSend("/topic/notice", JSON.toJSONString("广播：这是通知消息！！"));
    }

    /**
     * 具体用户消息
     */
    @GetMapping("/user/{name}")
    public void user(@PathVariable("name") String name) {
        messagingTemplate.convertAndSendToUser(name, "/topic/reply", JSON.toJSONString("密送：这是发送给" + name + "用户的消息！！"));
    }


}
