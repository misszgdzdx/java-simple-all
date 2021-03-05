package com.github.io.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * https://www.cnblogs.com/kiwifly/p/11729304.html
 * https://blog.csdn.net/qq_32331073/article/details/83411348
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 链接地址：websocket，指定使用SockJS协议
        registry.addEndpoint("/websocket").addInterceptors().setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅的路径前缀
        registry.enableSimpleBroker("/chat", "/group");
        // 前端发送的路径前缀
        registry.setApplicationDestinationPrefixes("/api");
    }
}
