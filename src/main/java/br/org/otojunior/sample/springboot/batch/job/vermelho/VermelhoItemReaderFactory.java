/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.job.vermelho;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
public class VermelhoItemReaderFactory {
	@Autowired
	private DataSource dataSource;
	 
    /**
     * 
     * @return
     */
    @Bean("vermelhoReader")
    public ItemReader<String> jdbcreader() {
    	JdbcCursorItemReader<String> reader = new JdbcCursorItemReader<>();
		reader.setSql(
			"select concat('vermelho',x) as item "
			+ "from generate_series(1,48)");
		reader.setDataSource(this.dataSource);
		reader.setRowMapper((rs, rnum) -> rs.getString("item"));
		return reader;
    }
}
