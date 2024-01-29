package br.com.api.domain.documento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	
	public List<Documento> findAllByBeneficiarioId(Long idBeneficiario);

}
