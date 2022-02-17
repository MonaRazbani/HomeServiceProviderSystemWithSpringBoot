package ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ServiceCategoryDto {
    @NotNull(message = "enter ServiceCategory Name")
    private String name ;
}
