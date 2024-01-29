package br.com.api.domain.beneficiario;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

@MockitoSettings
public class BeneficiarioServiceTest {

	private static final Long ID_BENEFICIARIO = 1L;

	@InjectMocks
	private BeneficiarioServiceImpl beneficiarioService;

	@Mock
	private BeneficiarioRepository beneficiarioRepository;

	private Beneficiario beneficiario;

	@BeforeEach
	public void setup() {
		beneficiario = Beneficiario.builder().dataInclusao(LocalDateTime.now())
				.dataNascimento(LocalDate.of(2000, 02, 20)).id(ID_BENEFICIARIO).nome("Maria da Silva")
				.telefone("11 99202020").build();
	}

	@Test
	void testAtualizacao() {
		BeneficiarioRequest request = new BeneficiarioRequest(ID_BENEFICIARIO, beneficiario.getNome(),
				beneficiario.getTelefone(), beneficiario.getDataNascimento());

		Mockito.when(beneficiarioRepository.getReferenceById(request.id())).thenReturn(beneficiario);
		Mockito.when(beneficiarioRepository.save(Mockito.any(Beneficiario.class))).thenReturn(beneficiario);

		Beneficiario resposta = beneficiarioService.atualizar(request);
		Assertions.assertThat(resposta.getDataAtualizacao()).isNotNull();
	}

	@Test
	void testBuscarPorId() {
		Mockito.when(beneficiarioRepository.findById(ID_BENEFICIARIO)).thenReturn(Optional.of(beneficiario));

		Beneficiario resposta = beneficiarioService.buscarPorId(ID_BENEFICIARIO);
		Assertions.assertThat(resposta).isNotNull();
	}

	@Test
	void testSalvar() {
		Mockito.when(beneficiarioRepository.save(Mockito.any(Beneficiario.class))).thenReturn(beneficiario);
		Beneficiario resposta = beneficiarioService.salvar(beneficiario);
		Assertions.assertThat(resposta).isNotNull();
	}

	@Test
	void testBuscar() {
		Pageable page = Pageable.ofSize(10);

		Mockito.when(beneficiarioRepository.findAll(page)).thenReturn(Page.empty());

		Page<Beneficiario> resposta = beneficiarioService.buscarTodos(page);
		Assertions.assertThat(resposta).isNotNull();
	}
	
}
