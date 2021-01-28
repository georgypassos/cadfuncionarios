package com.capgemini.georgy.cadfuncionarios.config.jwt;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Modelo de autenticação do usuário para requisição de token")
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;

	@ApiModelProperty(value = "Nome de usuário")
	private String username;
	
	@ApiModelProperty(value = "Senha do usuário")
	private String password;

	public JwtRequest() { }

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}