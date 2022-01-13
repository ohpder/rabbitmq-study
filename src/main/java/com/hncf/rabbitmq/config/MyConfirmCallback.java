/*
package com.hncf.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

*/
/**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: 丞风智能科技有限公司</p>
 *
 * @author OHP
 * @version 1.0 2022-01-13 11:47
 *//*

@Component
@Slf4j
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback*/
/*,RabbitTemplate.ReturnsCallback*//*
 {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostConstruct
    public void test() {
        rabbitTemplate.setConfirmCallback(this);
        // rabbitTemplate.setReturnsCallback(this);
    }
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack){
            log.error( "错误类型：：{}",correlationData.getId());
        }
    }

    */
/*@Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("交换机：{}，消息被退回：{},退回原因：{}",
                returned.getExchange(),new String(returned.getMessage().getBody()),returned.getReplyText());    }*//*

}
*/
