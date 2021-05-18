package uz.pdp.apppost.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotNull(message = "ism yoki familiya kiritilsin")
    private String firstName;

    @NotNull(message = "ism yoki familiya kiritilsin")
    private String lastName;

    @NotNull(message = "username kiritilsin")
    private String username;

    @NotNull(message = "parol kiritilsin")
    private String password;

    @NotNull(message = "parol takrori kiritilsin")
    private String prePassword;

}
