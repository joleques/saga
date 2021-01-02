package com.poc.saga.domain.modelo;

import java.util.List;

public interface PedidoRepositorio {
	
	void salvar(Pedido pedido);

	Pedido buscarPedidoPorCodigo(String codigo);

	void cancelar(String codigo);

	List<Pedido> buscarPedidosPorCPF(String cpf);
}
