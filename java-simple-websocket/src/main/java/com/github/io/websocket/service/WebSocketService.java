package com.github.io.websocket.service;

import com.github.io.websocket.entity.ChatRecord;

public interface WebSocketService {

    void sendMessage(ChatRecord record);
}
