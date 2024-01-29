package br.com.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.domain.beneficiario.Beneficiario;
import br.com.api.domain.beneficiario.BeneficiarioPostRequest;
import br.com.api.domain.beneficiario.BeneficiarioRequest;
import br.com.api.domain.beneficiario.BeneficiarioResponse;
import br.com.api.domain.beneficiario.BeneficiarioService;
import br.com.api.domain.documento.DocumentoResponse;
import br.com.api.domain.documento.DocumentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("beneficiarios")
@SecurityRequirement(name = "bearer-key")
public class BeneficiarioController {

	public static final String PATH_BENEFICIARIOS = "/beneficiarios/{id}";

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private DocumentoService documentoService;

	@PostMapping
	@Transactional
	public ResponseEntity<BeneficiarioResponse> cadastrar(@RequestBody @Valid BeneficiarioPostRequest request,
			UriComponentsBuilder uriBuilder) {

		var beneficiario = new Beneficiario(request);
		beneficiario = beneficiarioService.salvar(beneficiario);

		var documentos = documentoService.getDocumentos(request.documentos(), beneficiario.getId());
		documentoService.salvarTodos(documentos);

		var uri = uriBuilder.path(PATH_BENEFICIARIOS).buildAndExpand(beneficiario.getId()).toUri();

		return ResponseEntity.created(uri).body(new BeneficiarioResponse(beneficiario));
	}

	@GetMapping
	public ResponseEntity<Page<BeneficiarioResponse>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = beneficiarioService.buscarTodos(paginacao).map(BeneficiarioResponse::new);

		return ResponseEntity.ok(page);
	}

	@GetMapping(path = "/{id}/documentos")
	public ResponseEntity<List<DocumentoResponse>> listarDocumentosPorIdBeneficiario(@PathVariable Long id) {

		var response = documentoService.buscarTodosPorIdBeneficiario(id).stream()
				.map(documento -> documentoService.converteParaRecord(documento)).collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<BeneficiarioResponse> atualizar(@RequestBody @Valid BeneficiarioRequest request) {
		var beneficiario = beneficiarioService.atualizar(request);

		return ResponseEntity.ok(new BeneficiarioResponse(beneficiario));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<BeneficiarioResponse> excluir(@PathVariable Long id) {
		beneficiarioService.remover(id);

		return ResponseEntity.noContent().build();
	}

}
