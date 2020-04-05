package com.stefanini.model;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import com.stefanini.dao.PerfilDao;

import mockit.Mocked;
import mockit.Tested;

public class PerfilTest {

	@Tested Perfil perfil;
	
	
	@Test
	public void inserirPerfil(@Mocked PerfilDao dao) {
		
		Perfil perfil = new Perfil("ADMIN", "Administrador do esc", LocalDateTime.now(), LocalDateTime.now());
		
		dao.salvar(perfil);
		
		assertTrue(!dao.getList().isPresent());
	}
}
