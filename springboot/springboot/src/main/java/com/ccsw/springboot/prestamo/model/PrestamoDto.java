package com.ccsw.springboot.prestamo.model;

import java.time.LocalDate;

import com.ccsw.springboot.client.model.ClientDto;
import com.ccsw.springboot.game.model.GameDto;

/**
 * @author ccsw
 *
 */
public class PrestamoDto {

    private Long id;
    private GameDto game;
    private ClientDto client;
    private LocalDate fechaComienzo;
    private LocalDate fechaDevolucion;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the game
     */
    public GameDto getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(GameDto game) {
        this.game = game;
    }

    /**
     * @return the client
     */
    public ClientDto getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(ClientDto client) {
        this.client = client;
    }

    /**
     * @return the fechaComienzo
     */
    public LocalDate getFechaComienzo() {
        return fechaComienzo;
    }

    /**
     * @param fechaComienzo the fechaComienzo to set
     */
    public void setFechaComienzo(LocalDate fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    /**
     * @return the fechaDevolucion
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @param fechaDevolucion the fechaDevolucion to set
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

}
