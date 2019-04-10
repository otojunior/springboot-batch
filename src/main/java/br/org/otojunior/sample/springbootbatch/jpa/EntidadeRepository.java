/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

}
