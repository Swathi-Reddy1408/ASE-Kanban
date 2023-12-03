package com.kanban.backend.controller;

import java.util.Map;

//src/main/java/com/example/demo/controller/UserController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kanban.backend.User;
import com.kanban.backend.repository.UserRepository;
import com.kanban.backend.service.UserService;

@RestController
//@RequestMapping("/Signup")
public class UserController {

@Autowired
private UserRepository userRepository;
@Autowired
private UserService userService;

@CrossOrigin
@PostMapping("/Signup")
public ResponseEntity<?> signUp(@RequestBody User user) {
	
    // Check if user with the same email already exists
    if (userService.existsByEmail(user.getEmail())) 
    {
    System.out.println("This email already exists");
    return ResponseEntity.status(HttpStatus.OK)
    .body("This email already exists");      
    }
    else
    {
    userRepository.save(user);
    System.out.println("User Created succesfully");	
    return ResponseEntity.status(HttpStatus.CREATED)
    .body("User created successfully");
    }
}
/*@CrossOrigin
@PostMapping("/Login")
public ResponseEntity<?> Login(@RequestBody Map<String, String> userData) {
	String email=userData.get("email");
	String password=userData.get("password");
    
	System.out.println(email);
	System.out.println(password);
	System.out.println("I am here");
	String isUsernameOrEmailValid = userService.findByUsernameOrEmail(email, password);

    if (isUsernameOrEmailValid != null) {
        String body=isUsernameOrEmailValid;
        System.out.println(body);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
        	    .body(body);
    } else {
    	System.out.println("Invalid user email or Password");
    	return ResponseEntity.status(HttpStatus.ACCEPTED)
    		    .body("Invalid User email or Password"); // Or handle as needed
    }
	
    
}*/

}
