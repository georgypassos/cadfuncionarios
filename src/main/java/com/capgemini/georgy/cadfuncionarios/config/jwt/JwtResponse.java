package com.capgemini.georgy.cadfuncionarios.config.jwt;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Modelo de token retornado caso a autenticação seja bem sucedida")
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;

	@ApiModelProperty(value = "token gerado")
	private final String token;
	
	public JwtResponse() {
		this.token = null;
	}
	
	public JwtResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + "]";
	}
}