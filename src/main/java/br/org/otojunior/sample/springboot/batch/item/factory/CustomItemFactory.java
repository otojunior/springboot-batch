/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item.factory;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CustomItemFactory {
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CustomItemFactory.class);
	
	@Autowired
	private DataSource dataSource;
	 
    /**
     * 
     * @return
     */
    @Bean
    public ItemReader<String> jdbcreader() {
    	JdbcCursorItemReader<String> reader = new JdbcCursorItemReader<>();
		reader.setSql(
			"select concat('item',x) as item "
			+ "from generate_series(1,48)");
		reader.setDataSource(this.dataSource);
		reader.setRowMapper((rs, rnum) -> rs.getString("item"));
		return reader;
    }
}
