package com.poc.saga.application.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.poc.saga.application.servico.PedidoService;
import com.poc.saga.domain.modelo.Pedido;

public class ApiResponse {
	
	private int Status;
	
	private String mensagem;

	private static Logger logger = LoggerFactory.getLogger(PedidoService.class);

	private ApiResponse(int status, String mensagem) {
		Status = status;
		this.mensagem = mensagem;
	}

	public static ResponseEntity<ApiResponse> sucesso(Pedido pedido) {
		String mensagem = String.format( "Pedido [%s] realizado com sucesso!", pedido.getCodigo());
		logger.info(mensagem);
		return new ResponseEntity<ApiResponse>(new ApiResponse(200, mensagem), HttpStatus.OK);
	}

	public static ResponseEntity<ApiResponse> erro(String mensagem) {
		String mensagemErro = String.format( "Erro ao criar o pedido: %s", mensagem);
		logger.error(mensagem);
		return new ResponseEntity<ApiResponse>(new ApiResponse(400, mensagemErro), HttpStatus.BAD_REQUEST);
	}

	public int getStatus() {
		return Status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public static ResponseEntity<ApiResponse> sucesso(CancelarDTO cancelarDTO) {
		String mensagem = String.format( "Pedido [%s] cancelado com sucesso!", cancelarDTO.getCodigo());
		logger.info(mensagem);
		return new ResponseEntity<ApiResponse>(new ApiResponse(200, mensagem), HttpStatus.OK);
	}

}
