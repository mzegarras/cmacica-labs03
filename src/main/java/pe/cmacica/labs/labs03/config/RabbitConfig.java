package pe.cmacica.labs.labs03.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_CLIENTES = "clientes.exchange";


    public static final String QUEUE_APPLICATIONS="applications.queue";

    public static final String QUEUE_CLIENTES_CREATE = "clientes.create.queue";


    public static final String QUEUE_CLIENTES_UPDATE = "clientes.update.queue";


    public static final String QUEUE_CLIENTES_DELETE = "clientes.delete.queue";

    public static final String EXCHANGE_CLIENTES_CREATE = "clientes.create";
    public static final String EXCHANGE_CLIENTES_UPDATE = "clientes.update";
    public static final String EXCHANGE_CLIENTES_DELETE = "clientes.delete";
    public static final String EXCHANGE_CLIENTES_NOTIFY = "clientes.notify";

    @Bean
    @Qualifier(QUEUE_CLIENTES_CREATE)
    Queue clientesCreateQueue() {
        return QueueBuilder.durable(QUEUE_CLIENTES_CREATE).build();
    }

    @Bean
    @Qualifier(QUEUE_CLIENTES_UPDATE)
    Queue clientesDeleteQueue() {
        return QueueBuilder.durable(QUEUE_CLIENTES_UPDATE).build();
    }

    @Bean
    @Qualifier(QUEUE_CLIENTES_DELETE)
    Queue clientesUpdateQueue() {
        return QueueBuilder.durable(QUEUE_CLIENTES_DELETE).build();
    }

    @Bean
    @Qualifier(QUEUE_APPLICATIONS)
    Queue applicationQueue() {
        return QueueBuilder.durable(QUEUE_APPLICATIONS).build();
    }


    @Bean
    Exchange ordersExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_CLIENTES).build();
    }

    @Bean
    Binding bindingCreate(@Qualifier(QUEUE_CLIENTES_CREATE) Queue queue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(queue).to(ordersExchange).with(EXCHANGE_CLIENTES_CREATE);
    }

    @Bean
    Binding bindingUpdate(@Qualifier(QUEUE_CLIENTES_UPDATE) Queue queue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(queue).to(ordersExchange).with(EXCHANGE_CLIENTES_UPDATE);
    }

    @Bean
    Binding bindingDelete(@Qualifier(QUEUE_CLIENTES_DELETE) Queue queue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(queue).to(ordersExchange).with(EXCHANGE_CLIENTES_DELETE);
    }

    @Bean
    Binding bindingApplication(@Qualifier(QUEUE_APPLICATIONS) Queue queue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(queue).to(ordersExchange).with("#");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}