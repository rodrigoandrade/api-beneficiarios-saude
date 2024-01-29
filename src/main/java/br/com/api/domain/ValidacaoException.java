package br.com.api.domain;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = -6620027850060254037L;

	public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
