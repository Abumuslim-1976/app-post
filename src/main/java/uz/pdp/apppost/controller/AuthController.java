package uz.pdp.apppost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.LoginDTO;
import uz.pdp.apppost.payload.RegisterDTO;
import uz.pdp.apppost.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        ApiResponse apiResponse = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        ApiResponse apiResponse = authService.loginUser(loginDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
