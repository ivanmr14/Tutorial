package com.ccsw.springboot.client;

import java.util.List;

import com.ccsw.springboot.client.model.Client;
import com.ccsw.springboot.client.model.ClientDto;

/**
 * @author ccsw
 * 
 */
public interface ClientService {

    /**
     * Método para recuperar todos los {@link Client}
     *
     * @return {@link List} de {@link Client}
     */
    List<Client> findAll();

    /**
     * Método para crear o actualizar un {@link Client}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     * @throws Exception
     */
    void save(Long id, ClientDto dto) throws Exception;

    /**
     * Método para borrar un {@link Client}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un {@link Client} a partir del ID
     *
     * @param id PK de la entidad
     * @return {@link Client}
     */
    Client get(Long id);

}