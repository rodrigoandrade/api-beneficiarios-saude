package br.com.api.domain.beneficiario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {
	
	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	@Override
	public Beneficiario salvar(Beneficiario beneficiario) {
		return beneficiarioRepository.save(beneficiario);
	}

	@Override
	public Beneficiario buscarPorId(Long id) {
		return beneficiarioRepository.findById(id).orElseThrow();
	}
	
	@Override
	public Page<Beneficiario> buscarTodos(Pageable paginacao) {
		return beneficiarioRepository.findAll(paginacao);
	}
	
	@Override
	public void remover(Long id) {
		var beneficiario = beneficiarioRepository.getReferenceById(id);
        beneficiarioRepository.deleteById(beneficiario.getId());
	}

	@Override
	public Beneficiario atualizar(BeneficiarioRequest request) {
		Beneficiario beneficiario = beneficiarioRepository.getReferenceById(request.id());
		beneficiario.atualizarInformacoes(request);
		
		return beneficiarioRepository.save(beneficiario);
	}
	
}
