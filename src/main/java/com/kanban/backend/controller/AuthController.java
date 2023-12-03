package com.kanban.backend.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanban.backend.Role;
import com.kanban.backend.User;
import com.kanban.backend.config.JWTGenerator;
import com.kanban.backend.dto.AuthResponseDTO;
import com.kanban.backend.dto.LoginDto;
import com.kanban.backend.dto.RegisterDto;
import com.kanban.backend.repository.RoleRepository;
import com.kanban.backend.repository.UserRepository;
import com.kanban.backend.service.UserService;

import java.util.Collections;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private JWTGenerator jwtGenerator;
    private AuthResponseDTO authResponseDto;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
       // this.jwtGenerator = jwtGenerator;
    }

    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
    	System.out.println("I am here");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()));
        String email=loginDto.getEmail();
        String password=loginDto.getPassword();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String isUsernameOrEmailValid = userRepository.findByUsernameOrEmail(email, password);
        String body="";
        if (isUsernameOrEmailValid != null) 
        {
           body=isUsernameOrEmailValid;
        }
       String token = jwtGenerator.generateToken(authentication);
       //AuthResponseDTO responseDTO = new AuthResponseDTO();
       /*authResponseDto.setBody(body);
       authResponseDto.setAccessToken(token);
       System.out.println(authResponseDto.getBody());
       System.out.println(authResponseDto.getAccessToken());
  
        return new ResponseEntity<>("User login success!", HttpStatus.OK);

      // return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getUsername())) {
            return new ResponseEntity<>("This email already exists", HttpStatus.OK);
        }

        //System.out.println(registerDto.getFirstName());
        User user = new User();
        user.setEmail(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
