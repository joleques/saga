package com.poc.saga.domain.excecao;

public class ExcecaoNegocio extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcecaoNegocio(String message) {
		super(message);
	}
	
	

}
