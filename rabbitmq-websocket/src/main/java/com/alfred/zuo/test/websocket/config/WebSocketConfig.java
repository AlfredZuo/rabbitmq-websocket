package com.alfred.zuo.test.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * @author Anumbrella
 * @author mygodzj
 */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private MyChannelInterceptor inboundChannelInterceptor;

    @Autowired
    private AuthHandshakeInterceptor authHandshakeInterceptor;

    @Autowired
    private MyHandshakeHandler myHandshakeHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableStompBrokerRelay("/topic", "/queue");
                //不推荐采用硬编码的方式配置，已经在application.yml中设置了对应的参数
                //.setRelayHost("localhost")                // rabbitmq-host服务器地址
                //.setRelayPort(61613)                      // rabbitmq-stomp 服务器服务端口
                //.setClientLogin("guest")                  // 登陆账户
                //.setClientPasscode("guest");              // 登陆密码
        //定义一对一推送的时候前缀
        registry.setUserDestinationPrefix("/user/");
        //客户端需要把消息发送到/message/xxx地址，这条相当于是指导书中的/app路径前缀,未来由SimpAnnotationMethodMessageHandler处理
        registry.setApplicationDestinationPrefixes("/message");
        log.info("init rabbitmq websocket MessageBroker completed.");
    }

    /**
     * 连接站点配置
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")                                 //表示添加了一个/ws端点，客户端就可以通过这个端点来进行连接。
                .setAllowedOrigins("*")                                     //表示支持CORS
                .setHandshakeHandler(myHandshakeHandler)                    //这个方法是“自定义”处理拦截器，在这里可以验证Socket连接的用户是否可靠。
                .addInterceptors(authHandshakeInterceptor)                  //这个方法是添加一个“自定义”TCP手势处理连接操作，是在WebSocket连接建立之前的操作。
                .withSockJS();                                              //作用是开启SockJS支持
        log.info("init rabbitmq websocket endpoint ");
    }


    /**
     * 输入通道配置
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(inboundChannelInterceptor);
        registration.taskExecutor()    // 线程信息
                .corePoolSize(400)     // 核心线程池
                .maxPoolSize(800)      // 最多线程池数
                .keepAliveSeconds(60); // 超过核心线程数后，空闲线程超时60秒则杀死
    }

    /**
     * 消息传输参数配置
     *
     * @param registration
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setSendTimeLimit(15 * 1000)    // 超时时间
                .setSendBufferSizeLimit(512 * 1024) // 缓存空间
                .setMessageSizeLimit(128 * 1024);   // 消息大小
    }


}
