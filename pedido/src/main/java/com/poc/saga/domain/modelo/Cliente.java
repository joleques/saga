package com.poc.saga.domain.modelo;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class Cliente {

	private String nome;
	
	private String cpf;
	
	private Contato contato;

	public Cliente(String nome, String cpf, Contato contato) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.contato = contato;
		verify();
	}

	private void verify() {
		if (this.nome == null)
			throw new ExcecaoNegocio("Cliente invalido. Nome nulo.");
		

		if (this.contato == null)
			throw new ExcecaoNegocio("Cliente invalido. Contato nulo.");
		

		if (this.cpf == null)
			throw new ExcecaoNegocio("Cliente invalido. CPF nulo.");
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return this.contato.getEmail();
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return this.contato.getTelefone();
	}
	
}
