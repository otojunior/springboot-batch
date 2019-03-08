/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.validador;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class ValidacaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param mensagem
	 */
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
