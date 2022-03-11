package uz.pdp.pdpspring7thlessonn.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String resourceField;
    private Object object;

//    public ResourceNotFoundException(String resourceName, String resourceField, String object) {
//        this.resourceName = resourceName;
//        this.resourceField = resourceField;
//        this.object = object;
//    }
}
