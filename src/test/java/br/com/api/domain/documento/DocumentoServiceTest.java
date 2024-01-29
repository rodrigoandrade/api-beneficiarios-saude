package br.com.api.domain.documento;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.api.domain.beneficiario.Beneficiario;
import br.com.api.domain.documento.tipo.enums.TipoDocumento;

@MockitoSettings
public class DocumentoServiceTest {
	
	private static final Long ID_BENEFICIARIO = 1L;
	private static final Long ID_DOCUMENTO = 1L;
	private static final String NUMERO_RG = "2103655422";

	@InjectMocks
	private DocumentoServiceImpl documentoService;

	@Mock
	private DocumentoRepository documentoRepository;

	private Documento documento;
	
	@BeforeEach
	public void setup() {
		documento = Documento.builder().beneficiario(Beneficiario.builder().id(ID_DOCUMENTO).build())
				.descricao(NUMERO_RG)
				.tipoDocumento(TipoDocumento.RG)
				.build();
	}

	@Test
	void testAtualizacao() {
		DocumentoRequest request = new DocumentoRequest(ID_DOCUMENTO, TipoDocumento.RG, "2103655422", ID_BENEFICIARIO);

		Mockito.when(documentoRepository.getReferenceById(request.id())).thenReturn(documento);
		Mockito.when(documentoRepository.save(Mockito.any(Documento.class))).thenReturn(documento);

		Documento resposta = documentoService.atualizar(request);
		Assertions.assertThat(resposta.getDataAtualizacao()).isNotNull();
	}

	@Test
	void testBuscarPorId() {
		Mockito.when(documentoRepository.findById(ID_DOCUMENTO)).thenReturn(Optional.of(documento));

		Documento resposta = documentoService.buscarPorId(ID_DOCUMENTO);
		Assertions.assertThat(resposta).isNotNull();
	}

	@Test
	void testSalvar() {
		Mockito.when(documentoRepository.save(Mockito.any(Documento.class))).thenReturn(documento);
		Documento resposta = documentoService.salvar(documento);
		Assertions.assertThat(resposta).isNotNull();
	}

	@Test
	void testBuscar() {
		Pageable page = Pageable.ofSize(10);

		Mockito.when(documentoRepository.findAll(page)).thenReturn(Page.empty());

		Page<Documento> resposta = documentoService.buscarTodos(page);
		Assertions.assertThat(resposta).isNotNull();
	}

}
