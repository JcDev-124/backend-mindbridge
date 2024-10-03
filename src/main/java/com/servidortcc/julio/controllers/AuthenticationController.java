package com.servidortcc.julio.controllers;

import com.servidortcc.julio.domain.user.AuthenticationDTO;
import com.servidortcc.julio.domain.user.LoginResponseDTO;
import com.servidortcc.julio.domain.user.RegisterDTO;
import com.servidortcc.julio.domain.user.User;
import com.servidortcc.julio.infra.security.TokenService;
import com.servidortcc.julio.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private  AuthorizationService authorizationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(authorizationService.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        authorizationService.saveUser(data);
        return ResponseEntity.ok().build();
    }
}
