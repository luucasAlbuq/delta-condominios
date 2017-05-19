package services;

import java.util.List;

import model.condominio.Pessoa;

/**
 * Interface de serviços de uma pessoa
 * 
 * @author Lucas
 *
 */
public interface PessoaService {
	
	/**
	 * Persiste uma pessoa.
	 * 
	 * @param pessoa
	 */
	public void cadastrarUsuario(Pessoa pessoa);
	
	/**
	 * Atuliza uma pessoa cadastrada no sistema
	 * 
	 * @param pessoa
	 */
	public void atualizaPessoa(Pessoa pessoa);
	
	/**
	 * Remove uma pessoa do sistema
	 * 
	 * @param id
	 */
	public void removerPessoa(Long id);
	
	/**
	 * Retorna uma pessoa cadastrada no sistema
	 * 
	 * @param id
	 * @return pessoa
	 */
	public Pessoa consultarPessoa(Long id);
	
	/**
	 * Retorna todas as pessoas cadastradas no sistema.
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return pessoas cadastradas no sistema
	 */
	public List<Pessoa> consultarTodasAsPessoas(int pageNumber, int pageSize);
	
}
