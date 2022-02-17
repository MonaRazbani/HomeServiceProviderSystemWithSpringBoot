package ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdminDto {

    @NotNull(message = "You can't leave this empty.")
    private String username ;

    @NotNull(message = "You can't leave this empty.")
    private String password ;
}
