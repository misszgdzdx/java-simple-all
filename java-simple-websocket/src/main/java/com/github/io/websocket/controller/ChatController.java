package com.github.io.websocket.controller;

import com.github.io.websocket.entity.ChatRecord;
import com.github.io.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * https://www.cnblogs.com/qdhxhz/p/9438954.html
 * https://www.cnblogs.com/qdhxhz/p/9452237.html
 * https://www.lmlphp.com/user/6485/article/item/345339/
 * https://blog.csdn.net/weixin_41863745/article/details/81660735
 */
@Controller
public class ChatController {

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 前端设置发送地址：/api/single/chat + message
     */
    @MessageMapping("/single/chat")
    public void singleChat(ChatRecord record) {
        webSocketService.sendMessage(record);
    }
}
