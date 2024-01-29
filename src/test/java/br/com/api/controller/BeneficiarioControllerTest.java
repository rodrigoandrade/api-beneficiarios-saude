package br.com.api.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.domain.beneficiario.Beneficiario;
import br.com.api.domain.beneficiario.BeneficiarioPostRequest;
import br.com.api.domain.beneficiario.BeneficiarioRequest;
import br.com.api.domain.beneficiario.BeneficiarioResponse;
import br.com.api.domain.beneficiario.BeneficiarioService;
import br.com.api.domain.documento.Documento;
import br.com.api.domain.documento.DocumentoPostRequest;
import br.com.api.domain.documento.DocumentoResponse;
import br.com.api.domain.documento.DocumentoService;
import br.com.api.domain.documento.tipo.enums.TipoDocumento;

@MockitoSettings
public class BeneficiarioControllerTest {
	
	private static final Long ID_BENEFICIARIO = 1L;

	@InjectMocks
	private BeneficiarioController controller;
	
	@Mock
	private BeneficiarioService beneficiarioService;
	
	@Mock
	private DocumentoService documentoService;
	
	@Mock
	private UriComponentsBuilder uriBuilder;
	
	private Beneficiario beneficiario;
	
	@BeforeEach
	public void setup() {
		beneficiario = Beneficiario.builder().dataInclusao(LocalDateTime.now())
				.dataNascimento(LocalDate.of(2000, 02, 20))
				.id(ID_BENEFICIARIO)
				.nome("Maria da Silva")
				.telefone("11 99202020")
				.build();
	}
	
	@Test
	void testListagemDocumentosPorIdBeneficiario() {
		var rg = Documento.builder().beneficiario(Beneficiario.builder().id(ID_BENEFICIARIO).build())
			.dataInclusao(LocalDateTime.now())
			.descricao("1414444444")
			.tipoDocumento(TipoDocumento.RG)
			.build();
		
		DocumentoResponse documentoResponse = new DocumentoResponse(rg);
		
		Mockito.when(documentoService.buscarTodosPorIdBeneficiario(ID_BENEFICIARIO)).thenReturn(List.of(rg));
		Mockito.when(documentoService.converteParaRecord(Mockito.any(Documento.class))).thenReturn(documentoResponse);
		
		ResponseEntity<List<DocumentoResponse>> resposta = controller.listarDocumentosPorIdBeneficiario(ID_BENEFICIARIO);
		Assertions.assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void testAtualizarBeneficiario() {
		BeneficiarioRequest request = new BeneficiarioRequest(ID_BENEFICIARIO, beneficiario.getNome(), beneficiario.getTelefone(), beneficiario.getDataNascimento());
		
		Mockito.when(beneficiarioService.atualizar(request)).thenReturn(beneficiario);
		
		ResponseEntity<BeneficiarioResponse> resposta = controller.atualizar(request);
		Assertions.assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void testExcluirBeneficiario() {
		ResponseEntity<BeneficiarioResponse> resposta = controller.excluir(ID_BENEFICIARIO);
		Assertions.assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	@Test
	void testListarBeneficiario() {
		Pageable page = Pageable.ofSize(10);
		
		Mockito.when(beneficiarioService.buscarTodos(page)).thenReturn(Page.empty());
		
		ResponseEntity<Page<BeneficiarioResponse>> resposta = controller.listar(page);
		Assertions.assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	void testCadastrarBeneficiario() {
		UriComponentsBuilder uri = UriComponentsBuilder.fromPath("http:localhost:8080")
                .path("/beneficiarios");
		
		Mockito.when(beneficiarioService.salvar(Mockito.any(Beneficiario.class))).thenReturn(beneficiario);
		
		BeneficiarioPostRequest beneficiarioPostRequest = new BeneficiarioPostRequest(beneficiario.getNome(), beneficiario.getTelefone(), beneficiario.getDataNascimento(), new ArrayList<DocumentoPostRequest>());
		ResponseEntity<BeneficiarioResponse> resposta = controller.cadastrar(beneficiarioPostRequest, uri);
		
		Assertions.assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
	
}
