package co.analisys.gimnasio.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    // Configuración para miembros
    public static final String MIEMBRO_QUEUE = "miembro.registro.queue";
    public static final String MIEMBRO_EXCHANGE = "miembro.exchange";
    public static final String MIEMBRO_ROUTING_KEY = "miembro.registro";
    
    // Configuración para horarios
    public static final String HORARIO_QUEUE = "horario.cambio.queue";
    public static final String HORARIO_EXCHANGE = "horario.exchange";
    public static final String HORARIO_ROUTING_KEY = "horario.cambio";
    
    @Bean
    public Queue miembroRegistroQueue() {
        return new Queue(MIEMBRO_QUEUE, true);
    }

    @Bean
    public TopicExchange miembroExchange() {
        return new TopicExchange(MIEMBRO_EXCHANGE);
    }

    @Bean
    public Binding miembroBinding(Queue miembroRegistroQueue, TopicExchange miembroExchange) {
        return BindingBuilder.bind(miembroRegistroQueue).to(miembroExchange).with(MIEMBRO_ROUTING_KEY);
    }

    @Bean
    public Queue horarioCambioQueue() {
        return new Queue(HORARIO_QUEUE, true);
    }

    @Bean
    public TopicExchange horarioExchange() {
        return new TopicExchange(HORARIO_EXCHANGE);
    }

    @Bean
    public Binding horarioBinding(Queue horarioCambioQueue, TopicExchange horarioExchange) {
        return BindingBuilder.bind(horarioCambioQueue).to(horarioExchange).with(HORARIO_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        return factory;
    }
} 