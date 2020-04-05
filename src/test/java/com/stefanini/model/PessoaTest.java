package com.stefanini.model;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.junit.Test;

import com.stefanini.dao.PessoaDao;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;



public class PessoaTest {

	
	
	@Injectable PessoaDao pessoaDao;
	@Tested Pessoa pessoa;
	
	@PersistenceContext private EntityManager em;
	

	   @PostConstruct
	   private void beginTransactionIfNotYet() {
	      EntityTransaction transaction = em.getTransaction();

	      if (!transaction.isActive()) {
	         transaction.begin();
	      }
	   }

	   @PreDestroy
	   private void endTransactionWithRollbackIfStillActive() {
	      EntityTransaction transaction = em.getTransaction();

	      if (transaction.isActive()) {
	         transaction.rollback();
	      }
	   }

	   
	
	@Test
	public void salvarPessoa() {
		Pessoa pessoa1 = new Pessoa("Helena", "helena@hotmail.com", LocalDate.now(), Boolean.TRUE);
		
		pessoaDao.salvar(pessoa1);
		
		new Expectations() {{
            pessoa1.getNome();result = "Helena";
        }};
		
        new Verifications() {{
            pessoa1.getNome().contentEquals("Helena");
        }};
	}
	

	
	
}
