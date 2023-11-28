package com.ccsw.springboot.prestamo;

import org.springframework.data.domain.Page;

import com.ccsw.springboot.prestamo.model.Prestamo;
import com.ccsw.springboot.prestamo.model.PrestamoDto;
import com.ccsw.springboot.prestamo.model.PrestamoSearchDto;

/**
 * @prestamo ccsw
 *
 */
public interface PrestamoService {

    /**
     * Método para recuperar un {@link Prestamo} a partir de su ID
     *
     * @param id PK de la entidad
     * @return {@link Prestamo}
     */
    Prestamo get(Long id);

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Prestamo}
     */
    Page<Prestamo> findPage(PrestamoSearchDto dto);

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, PrestamoDto dto);

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un listado de prestamos {@link Prestamo}
     *
     * @return {@link List} de {@link Prestamo}
     */
    // List<Prestamo> findAll();

    /**
     * Recupera los préstamos filtrando opcionalmente por título del juego y/o
     * nombre del cliente
     *
     * @param title título del juego
     * @param name  nombre del cliente
     * @return {@link List} de {@link Prestamo}
     */
    // List<Prestamo> find(String title, String name);

}