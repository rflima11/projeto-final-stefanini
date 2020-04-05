package com.stefanini.servico;

import java.time.LocalDate;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;

import com.stefanini.dao.PessoaDao;
import com.stefanini.dao.PessoaPerfilDao;
import com.stefanini.model.Pessoa;
import com.stefanini.parsers.PessoaParserDTO;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class PessoaServicoTest {

	@Tested PessoaServico servico;
	
	@Injectable PessoaDao dao;
	
	@Injectable PessoaPerfilServico pessoaPerfilServico;
	
	@Injectable PessoaParserDTO parser;
	
	@PersistenceContext(unitName="jpa")
	protected EntityManager entityManager;
	
	
	@Test
	public void testaSalvarPessoa() {
		
		Pessoa pessoa1 = new Pessoa("Helena", "helena@hotmail.com", LocalDate.of(1994, 3, 26), Boolean.TRUE);
		pessoa1.setId(1L);
		
		dao.salvar(pessoa1);
		
		new Expectations() {{
			servico.salvar(pessoa1);
			
		}};
		
		new Verifications() {{
			System.out.println(servico.getList().get());
		}};
	}
}
