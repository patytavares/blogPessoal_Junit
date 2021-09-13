package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControladorTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Usuario usuario;
	private Usuario usuarioAlterar;
	
	@BeforeAll
	public void start() {
		usuario = new Usuario(0, "Victoria dos Santos","Vi@gmail.com", "1874652" );
		usuarioAlterar = new Usuario( 0, "Victoria dos Santos","Vic@gmail.com", "8954281" );
	}

	@Disabled
	@Test
	void deveSalvarUsuarioRetornaStatus201() {
		
		
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/salvar", HttpMethod.POST, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	void deveRetornarListadeUsuarioRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("Vitoria dos Santos", "1874652")
				.exchange("/api/v1/usuario/todos", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	
	@Test
	void deveAtualizarSenhaUsuarioRetornaStatus201() {
		
		
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAlterar);
		
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("RafaelBoaz", "134652")
				.exchange("/api/v1/usuario/alterar", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	

}