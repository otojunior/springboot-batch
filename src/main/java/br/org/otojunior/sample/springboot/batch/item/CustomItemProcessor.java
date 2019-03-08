/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import br.org.otojunior.sample.springboot.batch.validador.ValidacaoException;
import br.org.otojunior.sample.springboot.batch.validador.Validador;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomItemProcessor implements ItemProcessor<Resource, EntradaSaida> {
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private Validador validador;
	
	@Override
	public EntradaSaida process(Resource item) throws Exception {
		try {
			validador.validar(item);
			
			File tmpfile = File.createTempFile(item.getFilename(), ".gz");
			
			StreamUtils.copy(item.getInputStream(),
				new GZIPOutputStream(
				new FileOutputStream(tmpfile)));

			String location = "file:" + tmpfile.toString();
			return new EntradaSaida(item, resourceLoader.getResource(location));
		} catch (ValidacaoException e) {
			try (PrintWriter out = 
					new PrintWriter(
					new FileWriter(		
					new File("erros.txt"), true))) {
				out.println(item.getFilename() + ": " + e.getMessage());
			}
			FileUtils.moveFile(
				FileUtils.getFile(item.getFile().getAbsolutePath()), 
				FileUtils.getFile(item.getFile().getAbsolutePath() + ".processado"));
			return null;
		}
	}
}
