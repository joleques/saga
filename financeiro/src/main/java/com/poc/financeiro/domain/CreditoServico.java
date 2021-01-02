package com.poc.financeiro.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.poc.financeiro.application.ApiFinanceiro;

@Service
public class CreditoServico {

	private Logger LOGGER = LoggerFactory.getLogger(ApiFinanceiro.class);
	
	public void debitarValores(Cliente cliente) {
		String mensagem;
		if (cliente.getCpf().equals("11122233344")) {
			mensagem = "Não possui credito para realizar o pedido";
			LOGGER.info(mensagem);
			throw new ExcecaoNegocio(mensagem);
		}else {
			mensagem = "Pedido faturado com sucesso!";
			LOGGER.info(mensagem);
		}
	}
}
