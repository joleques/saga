package com.poc.saga.domain.modelo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class PedidoTest {


	@Test
	public void deveSubirExcecaoQuandoClienteQueFezPedidoForNulo() {
		List<Item> itens = List.of(new Item("TV Sony"));
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Pedido(null, itens), "Pedido invalido. Cliente que solicitou pedido está nulo.");
		
		Assertions.assertEquals("Pedido invalido. Cliente que solicitou pedido está nulo.", assertThrows.getMessage());
	}

	@Test
	public void deveSubirExcecaoQuandoPedidoNaoTemItens() {
		Contato contato = new Contato("teste@teste.com",null);
		Cliente cliente = new Cliente("Teste da Silva","94185296365", contato);
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Pedido(cliente, null), "Pedido invalido. Não possui itens.");
		
		Assertions.assertEquals("Pedido invalido. Não possui itens.", assertThrows.getMessage());
	}

	@Test
	public void deveSubirExcecaoQuandoPedidoListDeItensVazia() {
		Contato contato = new Contato("teste@teste.com",null);
		Cliente cliente = new Cliente("Teste da Silva", "94185296365", contato);
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Pedido(cliente, List.of()), "Pedido invalido. Não possui itens.");
		
		Assertions.assertEquals("Pedido invalido. Não possui itens.", assertThrows.getMessage());
	}
}
