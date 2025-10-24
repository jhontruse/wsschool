package com.pe.intranet.wsschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pe.intranet.wsschool.model.dto.JwtRequest;
import com.pe.intranet.wsschool.model.dto.JwtResponse;
import com.pe.intranet.wsschool.service.impl.AuthService;
import com.pe.intranet.wsschool.service.impl.JwtUserDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

//PASO 14 -JWT
@RestController
@AllArgsConstructor
@Tag(name = "Auth", description = "Gestión de login del sistema escolar")
@SecurityRequirement(name = "bearerAuth")
public class LoginController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private final AuthService authService;

    @Operation(summary = "Autenticación", description = "Autenticación de usuarios en el sistema escolar")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Parameter(description = "Credenciales", example = "JHONTRUSE / 123", required = true) @RequestBody JwtRequest req)
            throws Exception {
        authenticate(req.getUsername(), req.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(req.getUsername());
        final String token = authService.login(req.getUsername(), req.getPassword(), userDetails);
        return ResponseEntity.ok(new JwtResponse(token, "Bearer"));
    }

    private void authenticate(String username, String password) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
