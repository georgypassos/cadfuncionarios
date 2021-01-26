package com.capgemini.georgy.cadfuncionarios.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.capgemini.georgy.cadfuncionarios.config.jwt.JwtTokenUtil;

@Service
public class TokenAuthenticationService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

    public String createToken(String username) {
    	final UserDetails userDetails = new User(username, "", new ArrayList<>());
    	
    	return jwtTokenUtil.gerarToken(userDetails);
    }
}
