package one.digitalinnovation.gof.core.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.gof.core.client.dto.AddressDTO;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClientService {

    @GetMapping("/{zip}/json")
    AddressDTO checkZipCode(@PathVariable String zip);

}
