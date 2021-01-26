package com.capgemini.georgy.cadfuncionarios.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.georgy.cadfuncionarios.config.jwt.JwtSecurityConstants;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (JwtSecurityConstants.USUARIO.equals(username)) {
			return new User(username, JwtSecurityConstants.SENHA, new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Nome de usuário não encontrado: " + username);
		}
	}

}