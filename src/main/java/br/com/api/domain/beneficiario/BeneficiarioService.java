package br.com.api.domain.beneficiario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeneficiarioService {

	Beneficiario salvar(Beneficiario beneficiario);

	Beneficiario buscarPorId(Long id);

	Page<Beneficiario> buscarTodos(Pageable paginacao);

	void remover(Long id);

	Beneficiario atualizar(BeneficiarioRequest request);

}
