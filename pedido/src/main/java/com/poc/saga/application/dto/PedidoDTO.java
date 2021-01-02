package com.poc.saga.application.dto;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

	private String nome;
	
	private String email;
	
	private String telefone;
	
	private List<ItemDTO> itens;

	private String cpf;

	private String codigo;

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<ItemDTO> getItens() {
		if (this.itens == null || this.itens.isEmpty())
			return new ArrayList<ItemDTO>();
		
		return itens;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setItens(List<ItemDTO> itens) {
		this.itens = itens;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
		
	}

	
}
