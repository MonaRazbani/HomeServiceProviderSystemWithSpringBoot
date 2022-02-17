package ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OfferStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {

    private UUID identificationCode;

    private ExpertDto expert ;

    @NotNull
    @NumberFormat
    private String suggestedPrice ;

    @NotNull
    @NumberFormat
    private String suggestedDurationOfService ;

    @DateTimeFormat(pattern = "hh:mm")
    @NotNull
    private String startDate ;

    private OrderDto order;

    private OfferStatus status;
}
