package com.poc.saga.application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.saga.application.dto.ApiResponse;
import com.poc.saga.application.dto.CancelarDTO;
import com.poc.saga.application.dto.ClienteDTO;
import com.poc.saga.application.dto.ItemDTO;
import com.poc.saga.application.dto.PedidoDTO;
import com.poc.saga.application.servico.PedidoService;
import com.poc.saga.domain.modelo.Pedido;

@RestController
@RequestMapping("/pedido")
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private PedidoService servico;
	
	@PostMapping("")
	public ResponseEntity<ApiResponse> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
		try {
			Pedido pedido = servico.salvar(pedidoDTO);
			return ApiResponse.sucesso(pedido);
		} catch (Exception excecao) {
			return ApiResponse.erro(excecao.getMessage());
		}

	}

	@GetMapping("cliente/{codigoPedido}")
	public ClienteDTO dadosPedido(@PathVariable String codigoPedido) {
		return servico.buscarClientePor(codigoPedido);

	}

	@GetMapping("itens/{codigoPedido}")
	public List<ItemDTO> itensPedido(@PathVariable String codigoPedido) {
		return servico.buscarItensPor(codigoPedido);

	}

	@GetMapping("cliente/pedidos/{cpf}")
	public List<PedidoDTO> pedidos(@PathVariable String cpf) {
		return servico.buscarPedidosPor(cpf);

	}
	
	@RabbitListener( queues = "pedido_cancelado")
	public void canecelaPedido(String codigoPedido) {
		try {
			servico.cancelar(codigoPedido);
			logger.info(String.format("Pedido [%s] cancelado", codigoPedido));
		} catch (Exception excecao) {
			logger.info(String.format("Problemas ao cancelar o pedido [%s] - [%s]", codigoPedido, excecao.getMessage()));
		}

	}
}
