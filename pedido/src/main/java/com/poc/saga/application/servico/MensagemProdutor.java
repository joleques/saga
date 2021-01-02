package com.poc.saga.application.servico;

import com.poc.saga.domain.modelo.Pedido;

public interface MensagemProdutor {

	void envia(Pedido pedido);
}
