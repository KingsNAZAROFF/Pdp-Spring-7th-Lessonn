package uz.pdp.pdpspring7thlessonn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring7thlessonn.entity.User;
import uz.pdp.pdpspring7thlessonn.exceptions.ResourceNotFoundException;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.RegisterDto;
import uz.pdp.pdpspring7thlessonn.repository.LavozimRepository;
import uz.pdp.pdpspring7thlessonn.repository.UserRepository;
import uz.pdp.pdpspring7thlessonn.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("Parollar mos emas",false);
        }

        boolean byUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (byUsername){
            return new ApiResponse("Bunday username avval ro'yxatdan o'tgan !",false);
        }
        User user = new User();
        user.setFullName(registerDto.getFullName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setLavozim(lavozimRepository.findByName(AppConstants.USER).orElseThrow(()-> new ResourceNotFoundException("Lavozim ","name : ",AppConstants.USER)));
        user.setEnabled(true);
        userRepository.save(user);

        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz ",true);
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByEmail(username);
//        if (optionalUser.isPresent()){
//            return optionalUser.get();
//        }
//        throw  new UsernameNotFoundException(username +" topilmadi");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + "user topilmadi ! ! !"));
    }
}
