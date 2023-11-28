package com.ccsw.springboot.prestamo;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.springboot.prestamo.model.Prestamo;
import com.ccsw.springboot.prestamo.model.PrestamoDto;
import com.ccsw.springboot.prestamo.model.PrestamoSearchDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @prestamo ccsw
 *
 */
@Tag(name = "Prestamo", description = "API of Prestamo")
@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link PrestamoDto}
     */

    /*
     * @Operation(summary = "Find Page", description =
     * "Method that return a page of Prestamos")
     * 
     * @RequestMapping(path = "", method = RequestMethod.POST) public
     * Page<PrestamoDto> findPage(@RequestBody PrestamoSearchDto dto) {
     * 
     * Page<Prestamo> page = this.prestamoService.findPage(dto);
     * 
     * return new PageImpl<>( page.getContent().stream().map(e -> mapper.map(e,
     * PrestamoDto.class)).collect(Collectors.toList()), page.getPageable(),
     * page.getTotalElements());
     * 
     * }
     * 
     */

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link PrestamoDto}
     */

    @Operation(summary = "Find Page", description = "Method that return a page of Prestamos")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<PrestamoDto> findPage(@RequestBody PrestamoSearchDto dto) {

        if (dto.getFecha() != null) {
            dto.setFecha(dto.getFecha().minusDays(1));
        }

        Page<Prestamo> page = prestamoService.findPage(dto);

        return new PageImpl<>(
                page.getContent().stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList()),
                page.getPageable(), page.getTotalElements());

    }

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Prestamo")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody PrestamoDto dto) {
        this.prestamoService.save(id, dto);
    }

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Prestamo")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.prestamoService.delete(id);
    }

    /**
     * Recupera un listado de prestamos {@link Prestamo}
     *
     * @return {@link List} de {@link PrestamoDto}
     */

    /*
     * @Operation(summary = "Find", description =
     * "Method that return a list of Prestamos")
     * 
     * @RequestMapping(path = "", method = RequestMethod.GET) public
     * List<PrestamoDto> findAll() {
     * 
     * List<Prestamo> prestamos = this.prestamoService.findAll();
     * 
     * return prestamos.stream().map(e -> mapper.map(e,
     * PrestamoDto.class)).collect(Collectors.toList()); }
     * 
     */

    /**
     * Método para recuperar una lista de {@link Prestamo}
     *
     * @param title título del juego
     * @param name  nombre del cliente
     * @return {@link List} de {@link PrestamoDto}
     */
    /*
     * @Operation(summary = "Find", description =
     * "Method that return a filtered list of Prestamos")
     * 
     * @RequestMapping(path = "", method = RequestMethod.GET) public
     * List<PrestamoDto> find(@RequestParam(value = "title", required = false)
     * String title,
     * 
     * @RequestParam(value = "name", required = false) String name) {
     * 
     * List<Prestamo> prestamos = prestamoService.find(title, name);
     * 
     * return prestamos.stream().map(e -> mapper.map(e,
     * PrestamoDto.class)).collect(Collectors.toList());
     * 
     * }
     * 
     */

}