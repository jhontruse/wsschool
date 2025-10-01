package com.pe.intranet.wsschool.service;

import org.springframework.security.core.userdetails.UserDetails;

//PASO 14 -JWT
public interface IAuthService {

    public String login(String username, String password, UserDetails userDetails);

}
