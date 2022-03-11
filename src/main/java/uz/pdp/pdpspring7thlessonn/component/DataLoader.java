package uz.pdp.pdpspring7thlessonn.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.pdpspring7thlessonn.entity.Lavozim;
import uz.pdp.pdpspring7thlessonn.entity.User;
import uz.pdp.pdpspring7thlessonn.entity.enums.Permissions;
import uz.pdp.pdpspring7thlessonn.repository.LavozimRepository;
import uz.pdp.pdpspring7thlessonn.repository.UserRepository;
import uz.pdp.pdpspring7thlessonn.utils.AppConstants;

import java.util.Arrays;
import java.util.List;

import static uz.pdp.pdpspring7thlessonn.entity.enums.Permissions.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Value("${spring.datasource.initialization-mode}")
    private String initialModeType;

    @Override
    public void run(String... args) throws Exception {
       if (initialModeType.equals("always")){
           Permissions[] values = Permissions.values();
           Lavozim admin = lavozimRepository.save(new Lavozim(
                   AppConstants.ADMIN,
                   Arrays.asList(values),
                   "sistema egasi"
           ));
           Lavozim user = lavozimRepository.save(new Lavozim(
                   AppConstants.USER,
                   Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                   "Oddiy foydalanuvchi"
           ));
           userRepository.save(new User(
                   "Admin",
                   passwordEncoder.encode("admin"),
                   "admin123",
                   admin,
                   true

           ));
           userRepository.save(new User(
                   "User",
                   "user",
                   passwordEncoder.encode("user123"),
                   user,
                   true

           ));
       }

    }
}
