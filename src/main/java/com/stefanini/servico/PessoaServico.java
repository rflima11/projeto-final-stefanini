package com.stefanini.servico;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.PessoaDao;
import com.stefanini.dto.PessoaDto;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Pessoa;
import com.stefanini.parsers.PessoaParserDTO;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * 
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaServico implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String caminhoPasta = "C:\\AnexoTeste\\foto\\";

	@Inject
	private PessoaDao dao;

	@Inject
	private PessoaPerfilServico pessoaPerfilServico;
	
	@Inject
	private PessoaParserDTO parser;

	/**
	 * Salvar os dados de uma Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa salvar(@Valid Pessoa pessoa) {
		if(Objects.nonNull(pessoa.getBase64Imagem()) && pessoa.getBase64Imagem().split(",").length > 1) {
			pessoa.setImagem(this.salvarImagemAnexo(pessoa.getBase64Imagem(), pessoa.getEmail()));
		}
		return dao.salvar(pessoa);
	}
	/**
	 * Validando se existe pessoa com email
	 */
	public Boolean validarPessoa(@Valid Pessoa pessoa){
		if(pessoa.getId() != null){
			Optional<Pessoa> encontrar = dao.encontrar(pessoa.getId());
			if(encontrar.get().getEmail().equals(pessoa.getEmail())){
				return Boolean.TRUE;
			}
		}
		Optional<Pessoa> pessoa1 = dao.buscarPessoaPorEmail(pessoa.getEmail());
		return pessoa1.isEmpty();
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa atualizar(@Valid Pessoa entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) throws NegocioException {
		if(pessoaPerfilServico.buscarPessoaPerfil(id,null).count() == 0){
			dao.remover(id);
			return;
		}
		throw new NegocioException("Não foi possivel remover a pessoa");
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	public Optional<List<Pessoa>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
//	@Override
	public Optional<Pessoa> encontrar(Long id) {
		return dao.encontrar(id);
	}
	
	private String salvarImagemAnexo(String imageString, String email) {
	    String caminhoDaFoto = caminhoPasta + email + ".jpg";
	    imageString = imageString.split(",")[1];   
	    BufferedImage image = null;
	    byte[] imageByte;
	    
	    if(!new File(caminhoPasta).exists()) {
		      new File(caminhoPasta).mkdir();
		    }
	    
	    try {
	        imageByte = Base64.getDecoder().decode(imageString.getBytes());
	        
	        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
	        image = ImageIO.read(bais);
	        bais.close();
	        ImageIO.write(image, "JPG", new File(caminhoDaFoto));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return caminhoDaFoto;
	}
	
	private String carregarImagem(String email) {
	    String caminhoDaFoto = caminhoPasta + email + ".jpg";
	    
	    File arquivoF = new File(caminhoDaFoto);
	    return encodeFileToBase64(arquivoF);
	}
	
	
	private String encodeFileToBase64(File arquivoF) {
	    try {
	        byte[] fileContent = Files.readAllBytes(arquivoF.toPath());
	        return Base64.getEncoder().encodeToString(fileContent);
	    } catch (IOException e) {
	        throw new IllegalStateException("Não foi possível ler o arquivo " + arquivoF, e);
	    }
	}
	
	public String testeMetodo() {
		return "Teste bem sucedido";
	}
	

}
