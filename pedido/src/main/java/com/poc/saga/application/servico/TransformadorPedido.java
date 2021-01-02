package com.poc.saga.application.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.poc.saga.application.dto.ClienteDTO;
import com.poc.saga.application.dto.ItemDTO;
import com.poc.saga.application.dto.PedidoDTO;
import com.poc.saga.domain.modelo.Cliente;
import com.poc.saga.domain.modelo.Contato;
import com.poc.saga.domain.modelo.Item;
import com.poc.saga.domain.modelo.Pedido;

@Service
public class TransformadorPedido {

	public Pedido transformarDTOEmPedido(PedidoDTO dto) {
		if (dto == null)
			return null;

		Contato contato = new Contato(dto.getEmail(), dto.getTelefone());
		Cliente cliente = new Cliente(dto.getNome(), dto.getCpf(), contato);
		List<Item> itens = constroiItens(dto);
		return new Pedido(cliente, itens);
	}

	public ClienteDTO transformarPedidoEmClienteDTO(Pedido pedido) {
		if (pedido == null)
			return null;
		
		ClienteDTO dto = new ClienteDTO();
		dto.setCpf(pedido.getCPF());
		dto.setNome(pedido.getNomeDoCliente());
		dto.setEmail(pedido.getEmailDeContato());
		dto.setTelefone(pedido.getTelefoneDeContato());
		return dto;
	}

	public List<ItemDTO> transformarParaListaDeItens(Pedido pedido) {
		return pedido.getItens().stream().map(item -> {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setDescricao(item.getDescricao());
			return itemDTO;
		}).collect(Collectors.toList());
	}

	public List<PedidoDTO> transformarPorListaDe(List<Pedido> pedidos) {
		if (pedidos == null)
			return new ArrayList<PedidoDTO>();

		List<PedidoDTO> list = pedidos.stream().map(pedido -> {
			PedidoDTO dto = new PedidoDTO();
			dto.setCodigo(pedido.getCodigo());
			dto.setCpf(pedido.getCPF());
			dto.setNome(pedido.getNomeDoCliente());
			dto.setEmail(pedido.getEmailDeContato());
			dto.setTelefone(pedido.getTelefoneDeContato());
			dto.setItens(transformarParaListaDeItens(pedido));
			return dto;
		}).collect(Collectors.toList());
		return list;
	}

	private List<Item> constroiItens(PedidoDTO dto) {
		List<Item> itens = new ArrayList<Item>();
		for (ItemDTO itemDTO : dto.getItens()) {
			itens.add(new Item(itemDTO.getDescricao()));
		}
		return itens;
	}
}
