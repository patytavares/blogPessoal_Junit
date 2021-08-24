package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class TemaService {
	
	private @Autowired TemaRepository repositoryT;

	/**
	 * Método utilizado para alterar um tema. O mesmo retorna um Optional com Tema
	 * caso correto ou um Optional.empyt() caso id do tema não exista.
	 * 
	 * @param temaParaAlterar do tipo Tema
	 * @return Optional com Tema alterado
	 * @since 1.0
	 * @author Turma 28
	 */
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repositoryT.findById(temaParaAlterar.getId()).map(temaExistente -> {
			temaExistente.setTema(temaParaAlterar.getTema());
			return Optional.ofNullable(repositoryT.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}














