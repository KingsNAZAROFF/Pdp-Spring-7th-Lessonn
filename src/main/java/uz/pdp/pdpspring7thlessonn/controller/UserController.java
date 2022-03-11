package uz.pdp.pdpspring7thlessonn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.RegisterDto;
import uz.pdp.pdpspring7thlessonn.payload.UserDto;
import uz.pdp.pdpspring7thlessonn.service.AuthService;
import uz.pdp.pdpspring7thlessonn.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto){
        ApiResponse response = userService.addUser(userDto);
        return  ResponseEntity.status(response.isSuccess()?200:409).body(response);

    }
    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/editUser/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id,@Valid @RequestBody UserDto userDto){
        ApiResponse response = userService.editUser(id,userDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

}
