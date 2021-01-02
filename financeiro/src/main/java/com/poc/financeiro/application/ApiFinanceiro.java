package com.poc.financeiro.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.financeiro.application.servico.FinanceiroServico;


@Service
public class ApiFinanceiro {

	@Autowired
	private FinanceiroServico servico;
	
	@RabbitListener( queues = "financeiro")
	public void consume(String codigoPedido) {
		Logger LOGGER = LoggerFactory.getLogger(ApiFinanceiro.class);
		LOGGER.info(String.format("Recebida a menssagem [%s]", codigoPedido));
		
		servico.processaPedido(codigoPedido);
	}
}
