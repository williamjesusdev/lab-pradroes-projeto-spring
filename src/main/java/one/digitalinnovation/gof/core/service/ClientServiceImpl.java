package one.digitalinnovation.gof.core.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.gof.api.dto.ClientDTO;
import one.digitalinnovation.gof.core.client.dto.AddressDTO;
import one.digitalinnovation.gof.core.client.service.ViaCepClientService;
import one.digitalinnovation.gof.core.config.exception.NotFoundException;
import one.digitalinnovation.gof.domain.model.Address;
import one.digitalinnovation.gof.domain.model.Client;
import one.digitalinnovation.gof.domain.repository.AddressRepository;
import one.digitalinnovation.gof.domain.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClientService viaCepClient;

    @Override
    public Collection<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id " + id));
    }

    @Override
    public void create(ClientDTO clientDTO) {
        Client client = clientDTO.toEntity();
        handleAddressCheck(client);
        clientRepository.save(client);
    }

    @Override
    public void update(Long id, ClientDTO clientDTO) {
        findById(id);
        Client client = clientDTO.toEntity();
        client.setId(id);
        handleAddressCheck(client);
        clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private void handleAddressCheck(Client client) {
        String zip = client.getAddress().getZip();
        Address address = addressRepository.findById(zip).orElseGet(() -> {
            AddressDTO addressDTO = viaCepClient.checkZipCode(zip);
            if (Objects.nonNull(addressDTO) && Objects.nonNull(addressDTO.cep()))
                return addressRepository.save(addressDTO.toEntity());
            return null;
        });

        client.setAddress(address);
    }

}
