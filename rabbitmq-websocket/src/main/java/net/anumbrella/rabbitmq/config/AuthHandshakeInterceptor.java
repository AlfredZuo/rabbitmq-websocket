package net.anumbrella.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import java.util.Map;

/**
 * @author Anumbrella
 * @author mygodzj
 */
@Slf4j
@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        log.info("===============before handshake=============");
        // 在beforeHandshake中可以获取socket连接URL中的参数

        // 在这里可以获取session，做用户登录判断依据，这里只做了简单处理

        // HttpSession session = SpringContextUtils.getSession();
        // session.getAttribute("session_key") 判断具体的session存在
        // 比如，只有登录后，才可以进行websocket连接

        ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) serverHttpRequest;
        String user = serverRequest.getServletRequest().getParameter("user");
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("===============after handshake=============");
    }
}
