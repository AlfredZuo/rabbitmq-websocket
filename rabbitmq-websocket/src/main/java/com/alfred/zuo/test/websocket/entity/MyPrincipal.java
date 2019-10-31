package com.alfred.zuo.test.websocket.entity;

import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

/**
 * @author Anumbrella
 * @author mygodzj
 */
@Slf4j
public class MyPrincipal implements Principal {

    private String loginName;

    public MyPrincipal(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String getName() {
        return loginName;
    }
}
