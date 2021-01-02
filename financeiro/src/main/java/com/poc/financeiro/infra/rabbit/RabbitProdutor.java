package com.poc.financeiro.infra.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitProdutor {

	private static final String PEDIDO_CANCELADO = "pedido_cancelado";
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void cancelaPedido(String codigoPedido) {
		Message message = MessageBuilder.withBody(codigoPedido.getBytes()).build() ;
		rabbitTemplate.send(PEDIDO_CANCELADO, null, message);
	}

}
