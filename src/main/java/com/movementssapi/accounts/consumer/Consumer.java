package com.movementssapi.accounts.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @RabbitListener(queues = "cola_clientes_movimientos")
    public void receiveMessage(String message) {
        logger.info("Mensaje recibido movimientos consumer: " + message);
    }
}
