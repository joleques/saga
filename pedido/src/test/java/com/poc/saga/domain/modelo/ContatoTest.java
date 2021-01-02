package com.poc.saga.domain.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class ContatoTest {

	
	@Test
	public void deveSubirExcecaoQuandoNaoForPassadoEmailOuTelefone() {
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Contato(null, null), "É necessario pelo menos uma informação para contato.");

		Assertions.assertEquals("É necessario pelo menos uma informação para contato.", assertThrows.getMessage());
	}
	

	@Test
	public void naoDeveSubirExcecaoQuandoPassarEmail() {
		 new Contato("teste@teste.com", null);
	}
	

	@Test
	public void naoDeveSubirExcecaoQuandoPassarTelefone() {
		 new Contato(null, "5551978458596");
	}
}
