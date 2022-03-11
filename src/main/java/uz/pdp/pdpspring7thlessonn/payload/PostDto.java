package uz.pdp.pdpspring7thlessonn.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDto {

    @NotNull(message = "Tittle bo'sh bo'lmasligi kerak")
    private String title;

    @NotNull(message = "Text bo'sh bo'lmasligi kerak")
    private String text;


}
