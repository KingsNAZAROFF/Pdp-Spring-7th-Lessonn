package uz.pdp.pdpspring7thlessonn.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {
    @NotNull(message = "full name bo'sh bo'lmasligi kerak")
    private String fullName;
    @NotNull(message = "username bo'sh bo'lmasligi kerak")
    private String username;
    @NotNull(message = "Parol bo'sh bo'lmasligi kerak")
    private String  password;
    @NotNull(message ="Parol takrori bo'sh bo'lmasligi kerak")
    private String prePassword;
}
