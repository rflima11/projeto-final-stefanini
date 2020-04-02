package com.stefanini.model;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.stefanini.dao.PessoaDao;
import com.stefanini.resource.PessoaResource;
import com.stefanini.servico.PessoaServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;



public class PessoaTest {

	@Tested PessoaResource testePessoa;
	@Injectable PessoaDao dao;
	
	@Test
	public void testaRetornoDePessoa() {
		new Expectations() {{
			testePessoa.obterPessoas(); 
			result = Response.ok();
			
		}};
		
	//	testePessoa.obterPessoa(1L);
	}
	
}
