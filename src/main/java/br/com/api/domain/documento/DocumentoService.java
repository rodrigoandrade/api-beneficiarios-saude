package br.com.api.domain.documento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentoService {
	
	Documento salvar(Documento documento);
	
	List<Documento> salvarTodos(List<Documento> documemntos);

	Documento buscarPorId(Long id);

	Page<Documento> buscarTodos(Pageable paginacao);

	void remover(Long id);

	Documento atualizar(DocumentoRequest request);
	
	List<Documento> buscarTodosPorIdBeneficiario(Long idBeneficiario);
	
	List<Documento> getDocumentos(List<DocumentoPostRequest> documentos, Long idBeneficiario);

	DocumentoResponse converteParaRecord(Documento documento);

}
