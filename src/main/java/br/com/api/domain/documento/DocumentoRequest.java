package br.com.api.domain.documento;

import br.com.api.domain.documento.tipo.enums.TipoDocumento;
import jakarta.validation.constraints.NotNull;

public record DocumentoRequest(

		@NotNull Long id,

		TipoDocumento tipoDocumento,

		String descricao,

		Long idBeneficiario) {

}
