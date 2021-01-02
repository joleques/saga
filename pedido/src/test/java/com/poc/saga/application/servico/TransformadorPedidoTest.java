package com.poc.saga.application.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.poc.saga.application.dto.ClienteDTO;
import com.poc.saga.application.dto.ItemDTO;
import com.poc.saga.application.dto.PedidoDTO;
import com.poc.saga.domain.modelo.Cliente;
import com.poc.saga.domain.modelo.Contato;
import com.poc.saga.domain.modelo.Item;
import com.poc.saga.domain.modelo.Pedido;

public class TransformadorPedidoTest {

	private TransformadorPedido transformador;
	
	@BeforeEach
	public void setUp() {
		transformador = new TransformadorPedido();
	}
	
	@Test
	public void naoDeveTransformarQuandoPedidosNulo() {
		List<PedidoDTO> pedidosDTO = this.transformador.transformarPorListaDe(null);
		assertTrue(pedidosDTO.isEmpty());
	}
	
	@Test
	public void naoDeveTransformarEmPedidoQuandoDTOEhNulo() {
		Pedido pedido = this.transformador.transformarDTOEmPedido(null);
		assertNull(pedido);
	}
	
	@Test
	public void deveTransformarEmPedidoQuandoDTOEhValido() {
		PedidoDTO dto = new PedidoDTO();
		dto.setNome("Joao Pedro");
		dto.setEmail("joao.pedro@teste.com");
		dto.setCpf("98745632114");
		dto.setTelefone("5551987456321");
		
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setDescricao("Tv");
		
		dto.setItens(List.of(itemDTO));
		
		Pedido pedido = this.transformador.transformarDTOEmPedido(dto);


		assertEquals(pedido.getNomeDoCliente(), dto.getNome());
		assertEquals(pedido.getEmailDeContato(), dto.getEmail());
		assertEquals(pedido.getCPF(), dto.getCpf());
		assertEquals(pedido.getTelefoneDeContato(), dto.getTelefone());
		

		List<Item> itens = pedido.getItens();
		
		assertTrue(itens.size() == 1);
		
		Item item = itens.get(0);

		assertEquals(itemDTO.getDescricao(), item.getDescricao());
	}
	
	@Test
	public void naoDeveTransformarEmClienteQuandoPedidoEhNulo() {
		ClienteDTO clienteDTO = this.transformador.transformarPedidoEmClienteDTO(null);
		assertNull(clienteDTO);
	}
	
	@Test
	public void deveTransformarEmClienteQuandoPedidoEhValido() {
		Item itemDoPedido1 = new Item("TV Sony");
		
		Cliente cliente = construirCliente();
		
		Pedido pedido = new Pedido(cliente, List.of(itemDoPedido1));
		
		ClienteDTO clienteDTO = this.transformador.transformarPedidoEmClienteDTO(pedido);


		assertEquals(pedido.getNomeDoCliente(), clienteDTO.getNome());
		assertEquals(pedido.getEmailDeContato(), clienteDTO.getEmail());
		assertEquals(pedido.getCPF(), clienteDTO.getCpf());
		assertEquals(pedido.getTelefoneDeContato(), clienteDTO.getTelefone());
	}
	
	
	@Test
	public void deveTransformarListaDePedidosEmListaPedidosDTO() {
		List<Pedido> pedidos = new ArrayList<Pedido>();

		Item itemDoPedido1 = new Item("TV Sony");
		Item itemDoPedido2 = new Item("TV Sony");
		Item itemDoPedido3 = new Item("Barra de som");
		
		Cliente cliente = construirCliente();
		
		Pedido pedido1 = new Pedido(cliente, List.of(itemDoPedido1));
		pedidos.add(pedido1);
		
		Pedido pedido2 = new Pedido(cliente, List.of(itemDoPedido2, itemDoPedido3));
		pedidos.add(pedido2);
		
		List<PedidoDTO> pedidosDTO = this.transformador.transformarPorListaDe(pedidos);
		
		assertTrue(pedidosDTO.size() == 2);
		
		PedidoDTO pedidoDTO1 = pedidosDTO.get(0);
		
		assertEquals(pedido1.getNomeDoCliente(), pedidoDTO1.getNome());
		assertEquals(pedido1.getEmailDeContato(), pedidoDTO1.getEmail());
		assertEquals(pedido1.getCPF(), pedidoDTO1.getCpf());
		
		List<ItemDTO> itens = pedidoDTO1.getItens();
		
		assertTrue(itens.size() == 1);
		
		ItemDTO itemDTO = itens.get(0);

		assertEquals(itemDoPedido1.getDescricao(), itemDTO.getDescricao());
		

		PedidoDTO pedidoDTO2 = pedidosDTO.get(1);
		
		assertEquals(pedido2.getNomeDoCliente(), pedidoDTO2.getNome());
		assertEquals(pedido2.getEmailDeContato(), pedidoDTO2.getEmail());
		assertEquals(pedido2.getCPF(), pedidoDTO2.getCpf());
		
		List<ItemDTO> itens2 = pedidoDTO2.getItens();
		
		assertTrue(itens2.size() == 2);
		
		assertEquals(itemDoPedido2.getDescricao(), itens2.get(0).getDescricao());
		assertEquals(itemDoPedido3.getDescricao(), itens2.get(1).getDescricao());
	}

	private Cliente construirCliente() {
		Contato contato = new Contato("teste@teste.com", "5551985477896");
		Cliente cliente = new Cliente("Jorge Oleques", "98745632114", contato );
		return cliente;
	}
}
