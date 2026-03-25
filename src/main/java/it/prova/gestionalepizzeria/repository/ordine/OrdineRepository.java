package it.prova.gestionalepizzeria.repository.ordine;
import it.prova.gestionalepizzeria.model.Cliente;
import it.prova.gestionalepizzeria.model.ClienteProjectionInterface;
import it.prova.gestionalepizzeria.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Long>, JpaSpecificationExecutor<Ordine>
{
    @Query("select distinct o from Ordine o left join fetch o.pizze join fetch o.cliente where o.id = ?1")
    Ordine findSingleOrdineEager(Long id);

    @Query("select coalesce(sum(o.costoTotale), 0) from Ordine o where o.dataOrdine between ?1 and ?2")
    Float sumRicaviByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine);

    @Query("select coalesce(sum(p.prezzoBase), 0) from Ordine o join o.pizze p where o.dataOrdine between ?1 and ?2")
    Float sumCostiByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine);

    @Query("select count(o) from Ordine o where o.dataOrdine between ?1 and ?2")
    Integer countOrdiniByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine);

    @Query("select count(p) from Ordine o join o.pizze p where o.dataOrdine between ?1 and ?2")
    Integer countPizzeOrdinateByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine);

    @Query("select distinct o.cliente from Ordine o where o.dataOrdine between ?1 and ?2 and o.costoTotale > 100")
    List<Cliente> findClientiVirtuosiByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine);

    @Query("""
    select distinct c
    from Cliente c
    join c.ordini o
    where o.dataOrdine between ?1 and ?2
    and (
        select count(o2)
        from Ordine o2
        where o2.cliente = c
          and o2.closed = true
    ) = 9
""")
    List<Cliente> findClientiSilverByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine);

    @Query("""
    select distinct c
    from Cliente c
    join c.ordini o
    where o.dataOrdine between ?1 and ?2
    and (
        select count(o2)
        from Ordine o2
        where o2.cliente = c
          and o2.closed = true
    ) = 19
""")
    List<Cliente> findClientiGoldByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine);


}
