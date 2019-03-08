/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.validador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class Validador {
	/**
	 * 
	 * @param resource
	 */
	public void validar(Resource resource) throws ValidacaoException {
		/**
		 * Validação de arquivo vazio
		 */
		try {
			if (resource.getFile().length() == 0) {
				throw new ValidacaoException("Arquivo Vazio");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
