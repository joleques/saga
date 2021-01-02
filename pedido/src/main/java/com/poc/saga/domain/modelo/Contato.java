package com.poc.saga.domain.modelo;

import com.poc.saga.domain.excecao.ExcecaoNegocio;

public class Contato {

	private String email;
	
	private String telefone;

	public Contato(String email, String telefone) {
		this.email = email;
		this.telefone = telefone;
		verify();
	}

	private void verify() {
		if (this.email == null && this.telefone == null)
			throw new ExcecaoNegocio("É necessario pelo menos uma informação para contato.");
		
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}
	
	
}
