package br.com.api.domain.beneficiario;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record BeneficiarioRequest(

		@NotNull Long id,

		String nome,

		String telefone,

		LocalDate dataNascimento) {

}
