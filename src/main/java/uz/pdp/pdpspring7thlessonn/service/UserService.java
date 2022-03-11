package uz.pdp.pdpspring7thlessonn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.pdpspring7thlessonn.entity.Lavozim;
import uz.pdp.pdpspring7thlessonn.entity.User;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.UserDto;
import uz.pdp.pdpspring7thlessonn.repository.LavozimRepository;
import uz.pdp.pdpspring7thlessonn.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse addUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()){
            return new ApiResponse("Bunday username ro'yxatdan o'tgan",false);
        }
        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(userDto.getLavozimId());
        if (optionalLavozim.isEmpty()){
            return new ApiResponse("Bunday lavozim mavjud emas",false);
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setPassword(userDto.getPassword());
        user.setLavozim(optionalLavozim.get());
        userRepository.save(user);
        return new ApiResponse("User qo'shldi",true);

    }
    public ApiResponse editUser(Long id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new ApiResponse("Bunday User mavjud emas",false);
        }
        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(userDto.getLavozimId());
        if (optionalLavozim.isEmpty()){
            return new ApiResponse("Bunday lavozim mavjud emas",false);
        }
        User editingUser = optionalUser.get();
        editingUser.setUsername(userDto.getUsername());
        editingUser.setFullName(userDto.getFullName());
        editingUser.setPassword(userDto.getPassword());
        editingUser.setLavozim(optionalLavozim.get());
        userRepository.save(editingUser);
        return new ApiResponse("User ma'lumotlari o'zgartirildi",true);

    }
}
