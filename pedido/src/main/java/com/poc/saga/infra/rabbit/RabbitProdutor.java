package com.poc.saga.infra.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.saga.application.servico.MensagemProdutor;
import com.poc.saga.domain.modelo.Pedido;

@Service
public class RabbitProdutor implements MensagemProdutor {

	private static final String PEDIDO_REALIZADO = "pedido_realizado";
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void envia(Pedido pedido) {
		Message message = MessageBuilder.withBody(pedido.getCodigo().getBytes()).build() ;
		rabbitTemplate.send(PEDIDO_REALIZADO, null, message);
	}

}
