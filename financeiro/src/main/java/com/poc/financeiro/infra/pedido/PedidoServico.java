package com.poc.financeiro.infra.pedido;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PedidoServico {

	@Autowired
	private RestTemplate restTemplate;
	
	
	public ClienteDTO buscaPedido(String codigoPedido) {
		try {
			URI url = new URI("http://localhost:8081/pedido/cliente/" + codigoPedido);
			ResponseEntity<ClienteDTO> forEntity = restTemplate.getForEntity(url, ClienteDTO.class);
			return forEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
