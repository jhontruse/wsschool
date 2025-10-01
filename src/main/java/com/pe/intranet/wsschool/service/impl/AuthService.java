package com.pe.intranet.wsschool.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.pe.intranet.wsschool.model.entity.Menu;
import com.pe.intranet.wsschool.repository.IMenuRepository;
import com.pe.intranet.wsschool.security.JwtTokenUtil;
import com.pe.intranet.wsschool.service.IAuthService;

import lombok.RequiredArgsConstructor;

//PASO 14 -JWT
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private final IMenuRepository iMenuRepository;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password, UserDetails userDetails) {

        List<String> menus = iMenuRepository.getMenuByUsuario(username).stream()
                .map(Menu::getNombre)
                .toList();

        return jwtTokenUtil.generateToken(userDetails, menus);

    }

}
