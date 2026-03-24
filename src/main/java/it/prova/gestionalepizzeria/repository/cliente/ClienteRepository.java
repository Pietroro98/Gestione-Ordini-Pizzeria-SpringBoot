package it.prova.gestionalepizzeria.repository.cliente;

import it.prova.gestionalepizzeria.model.Cliente;
import it.prova.gestionalepizzeria.model.ClienteProjectionInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
  /*  @Query(value = """
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
    List<Cliente> searchAttiviByTerm(@Param("term") String term);*/

	@Query(value = """
            select
                c.id_cliente as value,
                concat(c.nome, ' ', c.cognome) as label,
                case
                    when (
                        select count(*)
                        from ordine o
                        where o.cliente_id = c.id_cliente
                          and o.closed = 1
                    ) + 1 = 20 then 'GOLD'
                    when (
                        select count(*)
                        from ordine o
                        where o.cliente_id = c.id_cliente
                          and o.closed = 1
                    ) + 1 = 10 then 'SILVER'
                    else 'NESSUNO'
                end as livelloPromo
            from cliente c
            where c.attivo = 1
              and (
                  upper(c.nome) like upper(concat('%', :term, '%'))
                  or upper(c.cognome) like upper(concat('%', :term, '%'))
              )
            order by c.cognome asc, c.nome asc
            """, nativeQuery = true)
	List<ClienteProjectionInterface> searchAttiviWithPromoByTerm(@Param("term") String term);
}
