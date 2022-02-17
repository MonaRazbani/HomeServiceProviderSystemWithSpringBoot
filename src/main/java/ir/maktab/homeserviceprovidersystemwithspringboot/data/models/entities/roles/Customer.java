package ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
public class Customer extends User {

}
