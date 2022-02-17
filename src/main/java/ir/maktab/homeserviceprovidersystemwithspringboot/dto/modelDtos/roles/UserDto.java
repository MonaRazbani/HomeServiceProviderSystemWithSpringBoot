package ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.Gender;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.RoleType;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.UserStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.validation.OnCustomerLogin;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.validation.OnCustomerSignup;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.validation.OnExpertLogin;
import ir.maktab.homeserviceprovidersystemwithspringboot.services.validation.OnExpertSignup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "You can't leave this empty.", groups = {OnCustomerSignup.class, OnExpertSignup.class})
    @Size(min = 3, message = "firstName is invalid",  groups = {OnCustomerSignup.class, OnExpertSignup.class})
    private String firstName;

    @NotNull(message = "You can't leave this empty.", groups = {OnCustomerSignup.class, OnExpertSignup.class})
    @Size(min = 3, message = "firstName is invalid", groups = {OnCustomerSignup.class, OnExpertSignup.class})
    private String lastName;

    @NotNull(message = "You can't leave this empty.", groups = {OnCustomerSignup.class, OnExpertSignup.class,
            OnExpertSignup.class, OnExpertLogin.class})
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email",
            groups = {OnCustomerSignup.class, OnCustomerLogin.class, OnExpertSignup.class, OnExpertLogin.class})
    private String email;

    private String username;

    @Size(min = 8, message = "password length's  must more than 8 ", groups = {OnCustomerSignup.class, OnCustomerLogin.class,
            OnExpertSignup.class, OnExpertLogin.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z]).{8,32}$", message = "password must contains words and numbers", groups = {OnCustomerSignup.class, OnCustomerLogin.class,
            OnExpertSignup.class, OnExpertLogin.class})
    private String password;

    private UserStatus status;

    private RoleType roleType;

    private Gender gender;

    private double credit;
}
