package uz.pdp.apppost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.apppost.entity.User;
import uz.pdp.apppost.exception.ResourceNotFoundException;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.LoginDTO;
import uz.pdp.apppost.payload.RegisterDTO;
import uz.pdp.apppost.repository.RoleRepository;
import uz.pdp.apppost.repository.UserRepository;
import uz.pdp.apppost.security.JwtProvider;
import uz.pdp.apppost.utils.AppConst;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " topilmadi"));
    }

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword()))
            return new ApiResponse("Parollar mos emas", false);

        if (userRepository.existsByUsername(registerDTO.getUsername()))
            return new ApiResponse("Bunday username bazada bor", false);

        User user = new User(
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleRepository.findByName(AppConst.USER).orElseThrow(() -> new ResourceNotFoundException("role", "name", "USER")),
                true
        );
        userRepository.save(user);
        return new ApiResponse("User saqlandi", true);
    }


    public ApiResponse loginUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
        ));
        User user = (User)authentication.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername());
        return new ApiResponse("User login qildi", true, token);
    }
}
