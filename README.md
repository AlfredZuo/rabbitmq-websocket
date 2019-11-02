# rabbitmq-websocket

Base RabbitMQ WebSocket Spring Boot demo


## 详细博文——[WebSocket学习](https://blog.csdn.net/Anumbrella/article/details/88594222).
```
一、需要安装rabbitmq并支持websocket&stomp模式
1、安装Erlang后安装Rabbitmq
2、Rabbit命令行窗口执行
    rabbitmq-plugins enable rabbitmq_management
    rabbitmq-plugins enable rabbitmq_stomp
    rabbitmq-plugins enable rabbitmq_web_stomp
二、系统设置为Maven工程并编译通过
```


### 前端/终端：[react-socket-client](./react-socket-client).

```
npm install

npm run start
```

```
http://localhost:3000
```

### 后端：[rabbitmq-websocket](./rabbitmq-websocket).
```
测试广播地址
http://localhost:8080/websocket/notice
测试密送地址
http://localhost:8080/websocket/user/211
http://localhost:8080/websocket/user/311
```








