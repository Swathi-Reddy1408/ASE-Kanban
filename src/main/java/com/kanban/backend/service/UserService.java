package com.kanban.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.backend.repository.UserRepository;


public interface UserService {

boolean existsByEmail(String email);
String findByUsernameOrEmail(String userName,String password);

}

