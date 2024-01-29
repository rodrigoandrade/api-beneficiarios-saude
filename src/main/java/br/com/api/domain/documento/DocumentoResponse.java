package br.com.api.domain.documento;

import br.com.api.domain.documento.tipo.enums.TipoDocumento;
import jakarta.validation.constraints.NotNull;

public record DocumentoResponse(

		@NotNull Long id,

		TipoDocumento tipoDocumento,

		String descricao) {

	public DocumentoResponse(Documento documento) {
		this(documento.getId(), documento.getTipoDocumento(), documento.getDescricao());
	}

}
