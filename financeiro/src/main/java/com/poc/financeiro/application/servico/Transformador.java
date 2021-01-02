package com.poc.financeiro.application.servico;

import org.springframework.stereotype.Service;

import com.poc.financeiro.domain.Cliente;
import com.poc.financeiro.infra.pedido.ClienteDTO;

@Service
public class Transformador {

	public Cliente transformar(ClienteDTO dto) {
		return new Cliente(dto.getNome(), dto.getCpf());
	}
}
