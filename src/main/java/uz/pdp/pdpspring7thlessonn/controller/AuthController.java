package uz.pdp.pdpspring7thlessonn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.pdpspring7thlessonn.entity.User;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.LoginDto;
import uz.pdp.pdpspring7thlessonn.payload.RegisterDto;
import uz.pdp.pdpspring7thlessonn.security.JwtProvider;
import uz.pdp.pdpspring7thlessonn.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){
        ApiResponse response = authService.registerUser(registerDto);
        return  ResponseEntity.status(response.isSuccess()?200:409).body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getLavozim());
        return ResponseEntity.ok(token);

    }

}
