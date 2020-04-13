package com.store.resource;

import com.store.exception.DuplicateUserEmailException;
import com.store.model.entity.User;
import com.store.model.request.LoginRequest;
import com.store.model.request.SignUpRequest;
import com.store.service.AuthenticationService;
import com.store.service.JwtService;
import com.store.service.StoreUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationResource {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StoreUserDetailsService storeUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/sign-up")
    public String signUp(@Valid @RequestBody SignUpRequest request, Model model) throws DuplicateUserEmailException {
        final User savedUser = authenticationService.signUp(request);
        model.addAttribute("response", new ResponseEntity<>(savedUser, HttpStatus.OK));
        return "sign_up_success";//todo: should redirect to html page Jayesh
    }

    @PostMapping("/login")
    public String authenticate(@Valid @RequestBody LoginRequest loginRequest, Model model){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword(), null));
        }catch (BadCredentialsException be){
            throw be;
        }

        UserDetails userDetails = storeUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        String jwt = jwtService.generateToken(userDetails);
        model.addAttribute("jwt", jwt);
        return jwt;
    }

}
