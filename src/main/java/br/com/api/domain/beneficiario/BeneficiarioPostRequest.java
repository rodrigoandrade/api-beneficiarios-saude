package br.com.api.domain.beneficiario;

import java.time.LocalDate;
import java.util.List;

import br.com.api.domain.documento.DocumentoPostRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BeneficiarioPostRequest(

		@NotBlank String nome,

		@NotBlank String telefone,

		@NotNull LocalDate dataNascimento,

		List<DocumentoPostRequest> documentos

) {

}
