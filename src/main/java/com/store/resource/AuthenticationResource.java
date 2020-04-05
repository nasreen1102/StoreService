package com.store.resource;

import com.store.exception.DuplicateUserEmailException;
import com.store.model.entity.User;
import com.store.model.request.UserSignUpRequest;
import com.store.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationResource {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public String signUp(@Valid @RequestBody UserSignUpRequest request, Model model) throws DuplicateUserEmailException {
        final User savedUser = authenticationService.signUp(request);
        model.addAttribute("response", new ResponseEntity<>(savedUser, HttpStatus.OK));
        return "sign_up_success";//todo: should redirect to html page Jayesh
    }
}
