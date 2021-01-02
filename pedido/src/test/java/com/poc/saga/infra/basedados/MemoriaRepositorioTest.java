package com.poc.saga.infra.basedados;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.saga.domain.excecao.ExcecaoNegocio;
import com.poc.saga.domain.modelo.Cliente;
import com.poc.saga.domain.modelo.Contato;
import com.poc.saga.domain.modelo.Item;
import com.poc.saga.domain.modelo.Pedido;

public class MemoriaRepositorioTest {

	
	private MemoriaRepositorio repositorio;
	
	@BeforeEach
	public void setUp() {
		repositorio = new MemoriaRepositorio();
	}

	@Test
	public void naoDeveSalvarPedidoQuandoPedidoEhNulo() {
		ExcecaoNegocio assertThrows = assertThrows(ExcecaoNegocio.class, () -> repositorio.salvar(null));
		assertEquals("Pedido invalido!", assertThrows.getMessage());
	}
	
	@Test
	public void deveSalvarPedidoEBuscarPeloCPF() {

		Item itemDoPedido1 = new Item("TV Sony");
		Item itemDoPedido2 = new Item("Barra de Som");
		
		Cliente cliente = construirCliente();
		
		Pedido pedido1 = new Pedido(cliente, of(itemDoPedido1));
		Pedido pedido2 = new Pedido(cliente, of(itemDoPedido2));
		
		repositorio.salvar(pedido1);
		repositorio.salvar(pedido2);
		
		List<Pedido> pedidos = repositorio.buscarPedidosPorCPF(cliente.getCpf());

		assertTrue(pedidos.size() == 2);
		assertEquals(pedido1, pedidos.get(0));
		assertEquals(pedido2, pedidos.get(1));
		
	}
	

	@Test
	public void deveSalvarPedidoEBuscarPeloCodigo() {

		Item itemDoPedido1 = new Item("TV Sony");
		Item itemDoPedido2 = new Item("Barra de Som");
		
		Cliente cliente = construirCliente();
		
		Pedido pedido1 = new Pedido(cliente, of(itemDoPedido1));
		Pedido pedido2 = new Pedido(cliente, of(itemDoPedido2));
		
		repositorio.salvar(pedido1);
		repositorio.salvar(pedido2);
		
		Pedido pedido = repositorio.buscarPedidoPorCodigo(pedido1.getCodigo());

		assertEquals(pedido, pedido1);
		
	}

	@Test
	public void deveCancelarPedido() {

		Item itemDoPedido1 = new Item("TV Sony");
		Item itemDoPedido2 = new Item("Barra de Som");
		
		Cliente cliente = construirCliente();
		
		Pedido pedido1 = new Pedido(cliente, of(itemDoPedido1));
		Pedido pedido2 = new Pedido(cliente, of(itemDoPedido2));
		
		repositorio.salvar(pedido1);
		repositorio.salvar(pedido2);
		
		List<Pedido> pedidos = repositorio.buscarPedidosPorCPF(cliente.getCpf());
		assertTrue(pedidos.size() == 2);
		assertEquals(pedido1, pedidos.get(0));
		assertEquals(pedido2, pedidos.get(1));
		
		repositorio.cancelar(pedido1.getCodigo());

		List<Pedido> pedidosDepoisCancelar = repositorio.buscarPedidosPorCPF(cliente.getCpf());

		assertTrue(pedidosDepoisCancelar.size() == 1);
		assertEquals(pedido2, pedidosDepoisCancelar.get(0));
		
	}
	
	private Cliente construirCliente() {
		Contato contato = new Contato("teste@teste.com", "5551985477896");
		Cliente cliente = new Cliente("Jorge Oleques", "98745632114", contato );
		return cliente;
	}
}
