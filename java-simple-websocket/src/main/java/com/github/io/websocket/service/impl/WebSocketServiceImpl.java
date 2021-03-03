package com.github.io.websocket.service.impl;

import com.github.io.websocket.entity.ChatRecord;
import com.github.io.websocket.service.WebSocketService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void sendMessage(ChatRecord record) {
        // 记录到数据库
        ChatRecord chat = mongoTemplate.insert(record, "chat");

        // TODO redis获取当前用户是否在线
        boolean online = true;

        // 发送给订阅者，发送记录
        if (online) {
            messagingTemplate.convertAndSend("/chat/single/" + record.getToUid(), record.getMsg());

            // 更新数据库状态
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(chat.getId()));
            Update update = Update.update("status", 1);
            UpdateResult result = mongoTemplate.upsert(query, update, "chat");
            System.out.println("执行条数：" + result.getModifiedCount());
        } else {
            // TODO 发送给队列继续通知
        }
    }
}
