package com.stefanini.servico;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import com.stefanini.model.Endereco;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CepServico {

	
	public Endereco converterCep(Endereco endereco) {
		ViaCEPClient client = new ViaCEPClient();
		try {
			ViaCEPEndereco enderecoCep = client.getEndereco(endereco.getCep());
			endereco.setBairro(enderecoCep.getBairro());
			endereco.setComplemento(enderecoCep.getComplemento());
			endereco.setLocalidade(enderecoCep.getLocalidade());
			endereco.setUf(enderecoCep.getUf());
			endereco.setLogradouro(enderecoCep.getLogradouro());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return endereco;
	}
}
