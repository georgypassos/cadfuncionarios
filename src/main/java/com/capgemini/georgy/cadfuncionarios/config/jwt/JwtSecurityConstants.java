package com.capgemini.georgy.cadfuncionarios.config.jwt;

public class JwtSecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 86_400_000; //10 dias
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/authenticate";
	public static final String USUARIO = "usuario01";
	public static final String SENHA = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";
}
