package com.poc.saga.domain.modelo;

import java.util.List;
import java.util.UUID;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class Pedido {
	
	private String codigo;

	private Cliente cliente;
	
	private List<Item> itens;

	public Pedido(Cliente cliente, List<Item> itens) {
		this.cliente = cliente;
		this.itens = itens;
		verify();
		this.codigo = UUID.randomUUID().toString();
	}
	
	private void verify() {
		if (this.cliente == null)
			throw new ExcecaoNegocio("Pedido invalido. Cliente que solicitou pedido está nulo.");
		

		if (this.itens == null || this.itens.isEmpty())
			throw new ExcecaoNegocio("Pedido invalido. Não possui itens.");
	}

	public String getCodigo() {
		return codigo;
	}

	public String alias() {
		return this.cliente.getCpf();
	}

	public String getCPF() {
		return this.cliente.getCpf();
	}

	public String getNomeDoCliente() {
		return this.cliente.getNome();
	}

	public String getEmailDeContato() {
		return this.cliente.getEmail();
	}

	public String getTelefoneDeContato() {
		return this.cliente.getTelefone();
	}

	public List<Item> getItens() {
		return itens;
	}

}
