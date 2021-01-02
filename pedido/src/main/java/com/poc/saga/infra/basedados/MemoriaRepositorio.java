package com.poc.saga.infra.basedados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.poc.saga.domain.excecao.ExcecaoNegocio;
import com.poc.saga.domain.modelo.Pedido;
import com.poc.saga.domain.modelo.PedidoRepositorio;

@Repository
public class MemoriaRepositorio implements PedidoRepositorio{

	Map<String, List<Pedido>> base = new HashMap<String, List<Pedido>>();
	
	@Override
	public void salvar(Pedido pedido) {
		verify(pedido);
		List<Pedido> pedidos = this.base.get(pedido.alias());
		if (pedidos == null || pedidos.isEmpty()) {
			List<Pedido> pedidosNovo = new ArrayList<Pedido>();
			pedidosNovo.add(pedido);
			this.base.put(pedido.alias(), pedidosNovo);
		}
		else
			pedidos.add(pedido);
	}

	@Override
	public Pedido buscarPedidoPorCodigo(String codigo) {
		List<Pedido> findFirst = this.base.values().stream()
		.map(pedidos -> {
			for (Pedido pedido : pedidos) {
				if (pedido.getCodigo().equals(codigo))
					return pedido;
			}
			return null;
		})
		.filter(pedido -> pedido != null)
		.collect(Collectors.toList());
		
		if (!findFirst.isEmpty())
			return findFirst.get(0);
		
		return null;
	}

	@Override
	public void cancelar(String codigo) {
		Pedido pedido = buscarPedidoPorCodigo(codigo);
		if (pedido != null) {
			List<Pedido> list = buscarPedidosPorCPF(pedido.alias());
			list.remove(pedido);
		}
	}

	@Override
	public List<Pedido> buscarPedidosPorCPF(String cpf) {
		return this.base.get(cpf);
	}

	private void verify(Pedido pedido) {
		if (pedido == null)
			throw new ExcecaoNegocio("Pedido invalido!");
	}


}
