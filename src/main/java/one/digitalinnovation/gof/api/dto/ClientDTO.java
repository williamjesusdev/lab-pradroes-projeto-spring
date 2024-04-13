package one.digitalinnovation.gof.api.dto;

import org.apache.commons.lang3.StringUtils;

import one.digitalinnovation.gof.core.config.exception.BadRequestException;
import one.digitalinnovation.gof.domain.model.Address;
import one.digitalinnovation.gof.domain.model.Client;

public record ClientDTO(String name, String zip, String addressNumber) {

    private static Client client;

    public ClientDTO {
        if (StringUtils.isBlank(name) || name.trim().length() < 2)
            throw new BadRequestException("Invalid value of name field!");
        if (StringUtils.isBlank(zip) || zip.trim().length() < 8)
            throw new BadRequestException("Invalid value of zip field!");
        if (StringUtils.isBlank(addressNumber))
            throw new BadRequestException("Invalid value of addressNumber field!");
    }

    public Client toEntity() {
        if (client == null) {
            client = new Client();
            client.setAddress(new Address());
            client.getAddress().setZip(zip);
            client.setAddressNumber(addressNumber);
            client.setName(name);
        }
        return client;
    }

}
