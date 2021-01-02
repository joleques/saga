package com.poc.saga.domain.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class ItemTest {

	@Test
	public void deveSubirExcecaoQuandoDescricaoDoItemForNula() {
		ExcecaoNegocio assertThrows = Assertions.assertThrows(ExcecaoNegocio.class, () -> new Item(null), "Item invalido. Descricao nulo.");
		
		Assertions.assertEquals("Item invalido. Descricao nulo.", assertThrows.getMessage());
	}
	
	@Test
	public void naoDeveSubirExcecaoQuandoDescricaoNaoForNula() {
		Item item = new Item("Tv Philco");
		
		Assertions.assertEquals("Tv Philco",item.getDescricao());
		Assertions.assertNotNull(item.getCodigo());
	}
}
