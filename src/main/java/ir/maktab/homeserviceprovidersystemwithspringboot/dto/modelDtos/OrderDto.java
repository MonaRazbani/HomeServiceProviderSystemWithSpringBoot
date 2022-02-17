package ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OrderStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.CustomerDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @Column(columnDefinition = "BINARY(16)")
    private UUID identificationCode;

    private CustomerDto customer;

    @NotNull(message = "You can't leave this empty.")
    @NumberFormat()
    private Double suggestedPrice;

    @NotNull(message = "You can't leave this empty.")
    private String explanation;

    private SubServiceDto subService;

    private Date performedOrder;

    private AddressDto address;

    private ExpertDto expert;

    private OrderStatus status;

    private CommentDto comment;


}
