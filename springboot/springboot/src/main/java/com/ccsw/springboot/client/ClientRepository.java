package com.ccsw.springboot.client;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.springboot.client.model.Client;

/**
 * @author ccsw
 *
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByName(String name);
}