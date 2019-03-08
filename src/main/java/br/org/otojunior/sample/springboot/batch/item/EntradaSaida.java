/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item;

import org.springframework.core.io.Resource;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class EntradaSaida {
	private Resource entrada;
	private Resource saida;

	/**
	 * @param entrada
	 * @param saida
	 */
	public EntradaSaida(Resource entrada, Resource saida) {
		this.entrada = entrada;
		this.saida = saida;
	}
	
	/**
	 * @return the entrada
	 */
	public Resource getEntrada() {
		return entrada;
	}
	/**
	 * @return the saida
	 */
	public Resource getSaida() {
		return saida;
	}
}
