
package net.anumbrella.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import net.anumbrella.rabbitmq.entity.MyPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * @author Anumbrella
 * @author mygodzj
 */
@Slf4j
@Component
public class MyHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 验证用户信息是否合法有效
        return super.determineUser(request, wsHandler, attributes);
    }

}
