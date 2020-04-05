package com.stefanini.servico;

import org.junit.Test;

import com.stefanini.dao.PerfilDao;
import com.stefanini.model.Perfil;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;

public class PerfilServicoTest {

	@Tested
	private PerfilServico servico;
	
	@Injectable 
	private PerfilDao dao;
	
	@Injectable
	private PessoaPerfilServico pessoaPerfilServico;
	
	
	
	@Test
	public void encontrarPerfil(@Mocked Perfil perfil) {
		
	
		new Expectations() {{
			dao.encontrar(1L);
			result = perfil;
		}};
	}
}
