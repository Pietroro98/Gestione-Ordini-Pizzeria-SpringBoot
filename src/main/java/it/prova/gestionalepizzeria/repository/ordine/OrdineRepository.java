package it.prova.gestionalepizzeria.repository.ordine;
import it.prova.gestionalepizzeria.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdineRepository extends JpaRepository<Ordine, Long>, JpaSpecificationExecutor<Ordine> {
}
