package com.hncf.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>功能描述：</p>
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: 丞风智能科技有限公司</p>
 *
 * @author OHP
 * @version 1.0 2022-01-12 18:27
 */
@Configuration
public class AmqpConfiguration {

    @Bean
    public FanoutExchange fanoutExchange() throws Exception {
        return new FanoutExchange("fanoutExchange",true,false);
    }
    @Bean
    public Exchange directExchange(){
        return new TopicExchange("topicExchange",true,false);
    }

    @Bean
    public Queue queue01() throws Exception {
        return new Queue("queue01",true, false,false);
    }
    @Bean
    public Queue queue02() throws Exception {
        return new Queue("queue02",true, false,false);
    }
    @Bean
    public Queue queue03() throws Exception {
        return new Queue("queue03",true, false,false);
    }
    @Bean
    public Binding fanoutExchangeBindingQueue01(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,@Qualifier("queue01") Queue queue) throws Exception {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    @Bean
    public Binding fanoutExchangeBindingQueue02(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,@Qualifier("queue02") Queue queue) throws Exception {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    @Bean
    public Binding fanoutExchangeBindingQueue03(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,@Qualifier("queue03") Queue queue) throws Exception {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding directExchangeBindingQueue01(@Qualifier("directExchange") Exchange directExchange,@Qualifier("queue01") Queue queue) throws Exception {
        return BindingBuilder.bind(queue).to(directExchange).with("direct.01").noargs();
    }
    @Bean
    public Binding directExchangeBindingQueue02(@Qualifier("directExchange") Exchange directExchange,@Qualifier("queue02") Queue queue) throws Exception {
        return BindingBuilder.bind(queue).to(directExchange).with("direct.02").noargs();
    }
    @Bean
    public Binding directExchangeBindingQueue03(@Qualifier("directExchange") Exchange directExchange,@Qualifier("queue03") Queue queue) throws Exception {
        return BindingBuilder.bind(queue).to(directExchange).with("direct.*").noargs();
    }

}
