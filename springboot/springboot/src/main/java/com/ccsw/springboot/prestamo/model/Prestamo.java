package com.ccsw.springboot.prestamo.model;

import java.time.LocalDate;

import com.ccsw.springboot.client.model.Client;
import com.ccsw.springboot.game.model.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author ccsw
 *
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "fechacomienzo", nullable = false)
    private LocalDate fechaComienzo;

    @Column(name = "fechadevolucion", nullable = false)
    private LocalDate fechaDevolucion;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client new value of {@link #getClient}.
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return fechaComienzo
     */
    public LocalDate getFechaComienzo() {
        return fechaComienzo;
    }

    /**
     * @param fechComienzo new value of {@link #getFechaComienzo}.
     */
    public void setFechaComienzo(LocalDate fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    /**
     * @return fechaDevolucion
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @param fechDevolucion new value of {@link #getFechaDevolucion}.
     */
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

}
