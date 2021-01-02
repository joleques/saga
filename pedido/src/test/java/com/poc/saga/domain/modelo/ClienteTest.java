package com.poc.saga.domain.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class ClienteTest {


	@Test
	public void deveSubirExcecaoQuandoClienteNaoPossuirNome() {
		Contato contato = new Contato("teste@teste.com", "5551978458596");
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Cliente(null, "94185296365", contato ), "Cliente invalido. Nome nulo.");
		Assertions.assertEquals("Cliente invalido. Nome nulo.", assertThrows.getMessage());
	}
	

	@Test
	public void deveSubirExcecaoQuandoClientePossuirNomeENaoPossuirContato() {
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Cliente("Teste da Silva","94185296365", null ), "Cliente invalido. Contato nulo.");
		Assertions.assertEquals("Cliente invalido. Contato nulo.", assertThrows.getMessage());
	}
	

	@Test
	public void deveSubirExcecaoQuandoClientePossuirNomeEPossuirContatoMasNaoPossuiCPF() {
		Contato contato = new Contato("teste@teste.com", "5551978458596");
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Cliente("Teste da Silva",null, contato ), "Cliente invalido. Contato nulo.");
		Assertions.assertEquals("Cliente invalido. CPF nulo.", assertThrows.getMessage());
	}


	@Test
	public void naoDeveSubirExcecaoQuandoClientePossuirNomeEPossuirContato() {
		Contato contato = new Contato("teste@teste.com", "5551978458596");
		new Cliente("Teste da Silva","94185296365", contato );
	}
	

}
