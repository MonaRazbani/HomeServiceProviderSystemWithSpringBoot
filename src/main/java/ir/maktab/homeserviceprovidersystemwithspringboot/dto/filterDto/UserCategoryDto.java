package ir.maktab.homeserviceprovidersystemwithspringboot.dto.filterDto;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.RoleType;
import lombok.Data;

@Data
public class UserCategoryDto extends BasePaginationDto {
    private String email;
    private String firstName;
    private String lastName;
    private String subServiceName;
    private RoleType roleType;
}
