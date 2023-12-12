package com.ccsw.springboot.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.springboot.client.model.Client;
import com.ccsw.springboot.client.model.ClientDto;

import jakarta.transaction.Transactional;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {

        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws Exception
     */
    @Override
    public void save(ClientDto dto) throws Exception {

        Client client;

        // Busco el nombre recibido y almaceno dicho cliente en el nuevo objeto Client
        Client clienteABuscar = clientRepository.findByName(dto.getName());

        client = new Client();

        client.setName(dto.getName());

        // En caso de no existir, se guarda el nuevo cliente. Si existe, se lanza
        // excepción
        if (clienteABuscar == null) {
            this.clientRepository.save(client);
        } else {
            throw new Exception();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.clientRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.clientRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(Long id) {

        return this.clientRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws Exception
     */
    @Override
    public void update(Long id, ClientDto dto) throws Exception {

        // Busco el nombre recibido y almaceno dicho cliente en el nuevo objeto Client
        Client clienteABuscar = clientRepository.findByName(dto.getName());

        // En caso de no existir, o ser el nombre del propio cliente se guarda el
        // cliente. Si existe, se lanza
        // excepción
        if (clienteABuscar == null || clienteABuscar.getId().equals(id)) {
            Client client;
            client = this.clientRepository.findById(id).orElse(null);
            client.setName(dto.getName());
            this.clientRepository.save(client);
        } else {
            throw new Exception();
        }
    }

}