package com.poc.financeiro.domain;


public class Cliente {

	private String nome;
	
	private String cpf;

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		verify();
	}

	private void verify() {
		if (this.nome == null)
			throw new ExcecaoNegocio("Cliente invalido. Nome nulo.");

		if (this.cpf == null)
			throw new ExcecaoNegocio("Cliente invalido. CPF nulo.");
		
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
}
