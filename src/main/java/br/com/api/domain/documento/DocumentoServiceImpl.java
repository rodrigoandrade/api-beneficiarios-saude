package br.com.api.domain.documento;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.api.domain.beneficiario.Beneficiario;

@Service
public class DocumentoServiceImpl implements DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Override
	public Documento salvar(Documento documento) {
		return documentoRepository.save(documento);
	}
	
	@Override
	public List<Documento> salvarTodos(List<Documento> documemntos) {
		return documentoRepository.saveAll(documemntos);
	}

	@Override
	public Documento buscarPorId(Long id) {
		return documentoRepository.findById(id).orElseThrow();
	}

	@Override
	public Page<Documento> buscarTodos(Pageable paginacao) {
		return documentoRepository.findAll(paginacao);
	}

	@Override
	public void remover(Long id) {
		var documento = documentoRepository.getReferenceById(id);
		documentoRepository.deleteById(documento.getId());
	}

	@Override
	public Documento atualizar(DocumentoRequest request) {
		Documento documento = documentoRepository.getReferenceById(request.id());
		documento.atualizarInformacoes(request);

		return documentoRepository.save(documento);
	}

	@Override
	public List<Documento> buscarTodosPorIdBeneficiario(Long idBeneficiario) {
		return documentoRepository.findAllByBeneficiarioId(idBeneficiario);
	}
	
	public List<Documento> getDocumentos(List<DocumentoPostRequest> documentos, Long idBeneficiario) {
		List<Documento> documentosBeneficiario = new ArrayList<>();
		if (nonNull(documentos)) {
			documentos.forEach(doc -> {
				var documento = Documento.builder()
					.beneficiario(Beneficiario.builder().id(idBeneficiario).build())
					.tipoDocumento(doc.tipoDocumento())
					.descricao(doc.descricao())
					.dataInclusao(LocalDateTime.now())
					.build();
				
				documentosBeneficiario.add(documento);
			});
		}
		return documentosBeneficiario;
	}

	@Override
	public DocumentoResponse converteParaRecord(Documento documento) {
		return new DocumentoResponse(documento.getId(), documento.getTipoDocumento(), documento.getDescricao());
	}

}
