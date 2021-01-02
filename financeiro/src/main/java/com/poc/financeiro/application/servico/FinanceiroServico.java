package com.poc.financeiro.application.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.financeiro.domain.Cliente;
import com.poc.financeiro.domain.CreditoServico;
import com.poc.financeiro.infra.pedido.ClienteDTO;
import com.poc.financeiro.infra.pedido.PedidoServico;
import com.poc.financeiro.infra.rabbit.RabbitProdutor;

@Service
public class FinanceiroServico {

	@Autowired
	private PedidoServico pedidoServico;
	
	@Autowired
	private Transformador transformador;
	
	@Autowired
	private CreditoServico creditoServico;

	@Autowired
	private RabbitProdutor produtor;
	
	public void processaPedido(String codigoPedido) {
		try {
			ClienteDTO clienteDTO = pedidoServico.buscaPedido(codigoPedido);
			Cliente cliente = transformador.transformar(clienteDTO);
			creditoServico.debitarValores(cliente);
		}catch (Exception e) {
			produtor.cancelaPedido(codigoPedido);
		}
	}

}
