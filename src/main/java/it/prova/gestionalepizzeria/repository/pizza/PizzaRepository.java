package it.prova.gestionalepizzeria.repository.pizza;
import it.prova.gestionalepizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long>, JpaSpecificationExecutor<Pizza> {
    List<Pizza> findByAttivoTrueOrderByDescrizioneAsc();
}
