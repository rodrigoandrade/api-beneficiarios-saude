
package br.com.api.domain.documento;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;

import br.com.api.domain.beneficiario.Beneficiario;
import br.com.api.domain.documento.tipo.enums.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "documentos")
@Entity(name = "Documento")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false, length = 20)
	private TipoDocumento tipoDocumento;

	@Column(name = "descricao", nullable = false, length = 100)
	private String descricao;

	@Column(name = "data_inclusao", nullable = false, updatable = false)
	private LocalDateTime dataInclusao;

	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;

	@ManyToOne
	@JoinColumn(name = "id_beneficiario", nullable = false)
	private Beneficiario beneficiario;

	public void atualizarInformacoes(DocumentoRequest request) {
		if (nonNull(request.tipoDocumento())) {
			this.tipoDocumento = request.tipoDocumento();
		}
		if (nonNull(request.descricao())) {
			this.descricao = request.descricao();
		}
		this.dataAtualizacao = LocalDateTime.now();
	}

}
