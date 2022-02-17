package ir.maktab.homeserviceprovidersystemwithspringboot.validation;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OrderStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.exceptions.EditionDenied;
import org.springframework.stereotype.Component;

@Component
public class ControlEdition {

    public boolean isValidToEdit(OrderStatus status) {
        if (status.equals(OrderStatus.STARTED) || status.equals(OrderStatus.WAITING_FOR_CHOOSING_EXPERT)) {
            return true;
        } else throw new EditionDenied();
    }
}
