
package br.com.api.domain.beneficiario;

import static java.util.Objects.nonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.api.domain.documento.Documento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "beneficiarios")
@Entity(name = "Beneficiario")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Beneficiario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "telefone", nullable = false, length = 20, unique = true)
	private String telefone;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@Column(name = "data_inclusao", nullable = false, updatable = false)
	private LocalDateTime dataInclusao;

	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;

	@OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Documento> documentos;
	
	public Beneficiario(BeneficiarioPostRequest request) {
		this.nome = request.nome();
		this.telefone = request.telefone();
		this.dataNascimento = request.dataNascimento();
		this.dataInclusao = LocalDateTime.now();
	}

	public void atualizarInformacoes(BeneficiarioRequest request) {
		if (nonNull(request.nome())) {
			this.nome = request.nome();
		}
		if (nonNull(request.telefone())) {
			this.telefone = request.telefone();
		}
		if (nonNull(request.dataNascimento())) {
			this.dataNascimento = request.dataNascimento();
		}
		this.dataAtualizacao = LocalDateTime.now();
	}
	
}
