package uz.pdp.apppost.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.apppost.entity.Permission;
import uz.pdp.apppost.entity.Role;
import uz.pdp.apppost.entity.User;
import uz.pdp.apppost.entity.enums.RoleName;
import uz.pdp.apppost.repository.RoleRepository;
import uz.pdp.apppost.repository.UserRepository;
import uz.pdp.apppost.utils.AppConst;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;


    @Override
    public void run(String... args){

        if (initialMode.equals("always")) {
            Permission[] permissions = Permission.values();
            Role admin = roleRepository.save(new Role(
                    AppConst.ADMIN,
                    Arrays.asList(permissions)
            ));

            Role user = roleRepository.save(new Role(
                    AppConst.USER,
                    Arrays.asList(Permission.ADD_COMMENT, Permission.DELETE_MY_COMMENT, Permission.EDIT_COMMENT)
            ));

            userRepository.save(new User(
                    "Admin",
                    "Adminov",
                    "admincha777",
                    passwordEncoder.encode("admin111"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "Userov",
                    "usercha444",
                    passwordEncoder.encode("user111"),
                    user,
                    true
            ));
        }


    }
}
