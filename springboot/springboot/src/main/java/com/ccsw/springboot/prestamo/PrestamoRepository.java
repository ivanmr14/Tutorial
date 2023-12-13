package com.ccsw.springboot.prestamo;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.springboot.client.model.Client;
import com.ccsw.springboot.game.model.Game;
import com.ccsw.springboot.prestamo.model.Prestamo;

/**
 * @author ccsw
 *
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param pageable pageable
     * @return {@link Page} de {@link Prestamo}
     */
    @Override
    @EntityGraph(attributePaths = { "game", "client" })
    Page<Prestamo> findAll(Specification<Prestamo> spec, Pageable pageable);

    /**
     * Método para comprobar si un juego está prestado
     *
     * @param Game      game
     * @param LocalDate fechaComienzo
     * @param LocalDate fechaDevolucion
     * @return {@link boolean}
     */
    boolean existsByGameAndFechaComienzoLessThanEqualAndFechaDevolucionGreaterThanEqual(Game game,
            LocalDate fechaDevolucion, LocalDate fechaComienzo);

    /**
     * Método para comprobar si un cliente tiene un préstamo activo
     *
     * @param Client    client
     * @param LocalDate fechaComienzo
     * @param LocalDate fechaDevolucion
     * @return {@link boolean}
     */
    boolean existsByClientAndFechaComienzoLessThanEqualAndFechaDevolucionGreaterThanEqual(Client client,
            LocalDate fechaDevolucion, LocalDate fechaComienzo);

}
