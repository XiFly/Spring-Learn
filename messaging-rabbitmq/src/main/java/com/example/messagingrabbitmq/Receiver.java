package com.example.messagingrabbitmq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 功能描述：
 * 消息接收器
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/22 14:46
 **/
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(100);

    public void receiveMessage(String message) {
        System.err.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
