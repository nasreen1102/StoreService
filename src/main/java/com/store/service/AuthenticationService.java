package com.store.service;

import com.store.exception.DuplicateUserEmailException;
import com.store.model.entity.User;
import com.store.model.request.SignUpRequest;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationService() {
    }

    public User signUp(SignUpRequest request) throws DuplicateUserEmailException {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new DuplicateUserEmailException("User email exists: "+request.getEmail());
        }
        User user = transform(request);
        return userRepository.save(user);
    }

    private User transform(SignUpRequest request) {
        User user = request.transform();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
