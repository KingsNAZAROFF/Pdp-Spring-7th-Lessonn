package uz.pdp.pdpspring7thlessonn.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.pdpspring7thlessonn.entity.enums.Permissions;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LavozimDto {
    @NotBlank
    private String name;


    private String description;

    @NotEmpty
    private List<Permissions> huquqList;
}
