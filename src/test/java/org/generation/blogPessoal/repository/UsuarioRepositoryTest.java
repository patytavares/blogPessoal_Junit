package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositorioTest {

	@Autowired
	private UsuarioRepository repositorio;
	
	@BeforeAll
	void start() {
		Usuario usuario = new Usuario(0, "Maria dos Santos", "edu@gmail.com","23456797");
		if(repositorio.findByUsuario(usuario.getUsuario())!=null)
			repositorio.save(usuario);
		
		usuario = new Usuario(0, "Felipe dos Santos", "Fe@gmail.com", "6543982" );
		if(repositorio.findByUsuario(usuario.getUsuario())!=null)
			repositorio.save(usuario);
		
		usuario = new Usuario(0, "Augusto dos Santos","Augusto@gmail.com", "134652" );
		if(repositorio.findByUsuario(usuario.getUsuario())!=null)
			repositorio.save(usuario);
	}
	
	@Test
	public void findByUsuarioRetornaUsuario() throws Exception {

		Usuario usuario = repositorio.findByUsuario("Maria dos Santos").get();
		assertTrue(usuario.getUsuario().equals("Maria dos Santos"));
	}
	
	@Test
	public void findAllByUsuarioContainingIgnoreCaseRetornaTresContato() {

		List<Usuario> listaDeUsuarios = repositorio.findAllByUsuarioContainingIgnoreCase(" Santos");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		repositorio.deleteAll();
		
		System.out.println("Teste Finalizado!");
	}

}