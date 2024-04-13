package one.digitalinnovation.gof.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String zip;
    private String place;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}
