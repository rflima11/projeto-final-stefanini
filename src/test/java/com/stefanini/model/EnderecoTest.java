package com.stefanini.model;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import com.stefanini.dao.EnderecoDao;

import mockit.Mocked;
import mockit.Tested;

public class EnderecoTest {
	@Tested Endereco endereco;
	
	
	@Test
	public void inserirEndereco(@Mocked EnderecoDao dao) {
		
		Endereco endereco = new Endereco();
		endereco.setBairro("taguatinga");
		endereco.setCep("72140471");
		endereco.setComplemento("Esquina");
		endereco.setIdPessoa(1L);
		endereco.setLocalidade("Na outra esquina");
		endereco.setLogradouro("casa 4");
		
		dao.salvar(endereco);
		
		assertTrue(!dao.getList().isPresent());
	}
}


