package one.digitalinnovation.gof.core.client.dto;

import one.digitalinnovation.gof.domain.model.Address;

public record AddressDTO(String cep, String logradouro, String complemento, String bairro,
        String localidade, String uf, String ibge, String gia, String ddd, String siafi) {

    private static Address address;

    public Address toEntity() {
        if (address == null) {
            address = new Address();
            address.setZip(cep);
            address.setPlace(logradouro);
            address.setComplement(complemento);
            address.setNeighborhood(bairro);
            address.setCity(localidade);
            address.setState(uf);
            address.setIbge(ibge);
            address.setGia(gia);
            address.setDdd(ddd);
            address.setSiafi(siafi);
        }
        return address;
    }

}
