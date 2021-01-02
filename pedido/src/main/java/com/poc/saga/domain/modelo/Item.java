package com.poc.saga.domain.modelo;

import java.util.UUID;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class Item {

	private String codigo;
	
	private String descricao;

	public Item(String descricao) {
		this.descricao = descricao;
		verify();
		this.codigo = UUID.randomUUID().toString();
	}

	private void verify() {
		if (this.descricao == null)
			throw new ExcecaoNegocio("Item invalido. Descricao nulo.");
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
