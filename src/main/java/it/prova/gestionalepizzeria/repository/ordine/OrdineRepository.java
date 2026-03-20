package it.prova.gestionalepizzeria.repository.ordine;
import it.prova.gestionalepizzeria.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrdineRepository extends JpaRepository<Ordine, Long>, JpaSpecificationExecutor<Ordine> {
    @Query("select distinct o from Ordine o left join fetch o.pizze join fetch o.cliente where o.id = ?1")
    Ordine findSingleOrdineEager(Long id);
}
