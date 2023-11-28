package com.ccsw.springboot.prestamo.model;

import java.time.LocalDate;

import com.ccsw.springboot.common.pagination.PageableRequest;

/**
 * @author ccsw
 *
 */
public class PrestamoSearchDto {

    private Long idJuego;
    private Long idCliente;
    private LocalDate fecha;
    private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    /**
     * @return the idJuego
     */
    public Long getIdJuego() {
        return idJuego;
    }

    /**
     * @param idJuego the idJuego to set
     */
    public void setIdJuego(Long idJuego) {
        this.idJuego = idJuego;
    }

    /**
     * @return the idCliente
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}