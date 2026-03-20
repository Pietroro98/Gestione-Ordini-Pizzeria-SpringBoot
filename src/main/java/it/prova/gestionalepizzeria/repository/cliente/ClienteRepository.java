package it.prova.gestionalepizzeria.repository.cliente;

import it.prova.gestionalepizzeria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
    @Query(value = """
			select *
			from cliente c
			where c.attivo = true
			and (
				upper(c.cognome) like upper(concat('%', :term, '%'))
				or upper(c.nome) like upper(concat('%', :term, '%'))
				or upper(concat(trim(c.nome), ' ', trim(c.cognome))) like upper(concat('%', :term, '%'))
				or upper(concat(trim(c.cognome), ' ', trim(c.nome))) like upper(concat('%', :term, '%'))
			)
			order by c.cognome asc, c.nome asc
			""", nativeQuery = true)
    List<Cliente> searchAttiviByTerm(@Param("term") String term);
}
