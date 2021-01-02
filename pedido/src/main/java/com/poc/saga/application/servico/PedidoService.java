package com.poc.saga.application.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poc.saga.application.dto.CancelarDTO;
import com.poc.saga.application.dto.ClienteDTO;
import com.poc.saga.application.dto.ItemDTO;
import com.poc.saga.application.dto.PedidoDTO;
import com.poc.saga.domain.modelo.Pedido;
import com.poc.saga.domain.modelo.PedidoRepositorio;

@Service
public class PedidoService {
	
	private TransformadorPedido transformador;
	
	private PedidoRepositorio repositorio;
	
	private MensagemProdutor produtor;

	public PedidoService(TransformadorPedido transformador, PedidoRepositorio repositorio, MensagemProdutor produtor) {
		this.transformador = transformador;
		this.repositorio = repositorio;
		this.produtor = produtor;
	}


	public Pedido salvar(PedidoDTO dto) {
		Pedido pedido = transformador.transformarDTOEmPedido(dto);
		repositorio.salvar(pedido);
		produtor.envia(pedido);
		return pedido;
	}


	public ClienteDTO buscarClientePor(String codigo) {
		Pedido pedido = repositorio.buscarPedidoPorCodigo(codigo);
		return transformador.transformarPedidoEmClienteDTO(pedido);
	}


	public List<ItemDTO> buscarItensPor(String codigo) {
		Pedido pedido = repositorio.buscarPedidoPorCodigo(codigo);
		return transformador.transformarParaListaDeItens(pedido);
	}


	public void cancelar(String codigo) {
		repositorio.cancelar(codigo);
	}


	public List<PedidoDTO> buscarPedidosPor(String cpf) {
		List<Pedido> pedidos = repositorio.buscarPedidosPorCPF(cpf);
		return transformador.transformarPorListaDe(pedidos);
	}

}
