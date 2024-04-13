package one.digitalinnovation.gof.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.gof.domain.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
