package com.stefanini.servico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.junit.Test;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Endereco;
import com.stefanini.parsers.EnderecoParserDTO;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;

public class EnderecoServicoTest {

	
	@Tested EnderecoServico servico;
	
	@Injectable EnderecoDao dao;
	
	@Injectable CepServico cep;
	
	@Injectable EnderecoParserDTO dto;
	
	@Injectable GenericDao genericDao;
	
	@PersistenceContext(unitName="jpa")
	protected EntityManager em;
	
	
	
	@Test
	public void buscarEnderecos(@Mocked Endereco endereco) {
		
	
		new Expectations() {{
			dao.encontrar(1L);
			result = endereco;
		}};
	}
}
