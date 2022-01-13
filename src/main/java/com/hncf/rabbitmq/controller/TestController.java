package com.hncf.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.postprocessor.DeflaterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: 丞风智能科技有限公司</p>
 *
 * @author OHP
 * @version 1.0 2022-01-13 09:38
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/01")
    public String test01() throws ExecutionException, InterruptedException {
        // rabbitTemplate.convertAndSend("fanoutExchange","12312","11111111111");
        CorrelationData correlationData = new CorrelationData("xiang");
        rabbitTemplate.convertAndSend("dasd","queuedas01","default....", correlationData);
        SettableListenableFuture<CorrelationData.Confirm> future = correlationData.getFuture();
        CorrelationData.Confirm confirm = future.get();
        if (!confirm.isAck()){
            throw new RuntimeException(confirm.getReason());
        }
        return "发送成功";
    }
    @RabbitListener(queues = "queue01")
    public void queue01(String message){
        log.error("queue01::"+message);
    }
    private AtomicBoolean atomicBoolean = new AtomicBoolean();
    @RabbitListener(queues = "queue01")
    public void queue02(String message){
        if (atomicBoolean.getAndSet(!atomicBoolean.get())){
            throw new RuntimeException("111");
        }
        log.error("queue02::"+message);
    }
    @RabbitListener(queues = "queue03")
    public void queue03(String message){
        log.error("queue03::"+message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    name = "test"
            ), exchange = @Exchange(name="test")))
    public void aa(String message){
        log.error("aa::"+message);
    }
}
