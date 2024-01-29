package br.com.api.domain.beneficiario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BeneficiarioResponse(

		Long id, String nome, String email, LocalDate dataNascimento, LocalDateTime dataInclusao) {

	public BeneficiarioResponse(Beneficiario beneficiario) {
		this(beneficiario.getId(), beneficiario.getNome(), beneficiario.getTelefone(), beneficiario.getDataNascimento(),
				beneficiario.getDataInclusao());
	}

}
