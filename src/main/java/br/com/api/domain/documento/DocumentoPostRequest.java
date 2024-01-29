package br.com.api.domain.documento;

import br.com.api.domain.documento.tipo.enums.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DocumentoPostRequest(

		@NotNull TipoDocumento tipoDocumento,

		@NotBlank String descricao) {

}
