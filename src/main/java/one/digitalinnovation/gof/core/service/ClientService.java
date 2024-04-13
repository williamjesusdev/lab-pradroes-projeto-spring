package one.digitalinnovation.gof.core.service;

import java.util.Collection;

import one.digitalinnovation.gof.api.dto.ClientDTO;
import one.digitalinnovation.gof.domain.model.Client;

public interface ClientService {

    Collection<Client> findAll();

    Client findById(Long id);

    void create(ClientDTO clientDTO);

    void update(Long id, ClientDTO clientDTO);

    void delete(Long id);

}
