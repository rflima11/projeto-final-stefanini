package com.stefanini.servico;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.model.Endereco;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnderecoServico implements Serializable {
	
	@Inject
	private EnderecoDao dao;

	@Inject
	private CepServico cep;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

	public Endereco salvar(@Valid Endereco entity) {
		return dao.salvar(cep.converterCep(entity));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

	public Endereco atualizar(@Valid Endereco entity) {
		return dao.atualizar(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

	public void remover(Long id) {
	dao.remover(id);
	}


	public Optional<List<Endereco>> getList() {
		return dao.getList();
	}

	public Optional<Endereco> encontrar(Long id) {
		return dao.encontrar(id);
	}
	
	public String encontrarPorCep(@Valid Endereco endereco) {
		return cep.buscarCep(endereco);
	}
}
