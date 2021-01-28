package com.capgemini.georgy.cadfuncionarios.cadfuncionarios;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.georgy.cadfuncionarios.config.jwt.JwtSecurityConstants;
import com.capgemini.georgy.cadfuncionarios.service.TokenAuthenticationService;
import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegracaoTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	private String testToken = null;
	
	@Test
	public void deveObterFuncionariosUrlPublica_entaoHttpOk() throws Exception {
		this.mockMvc.perform(get("/api/public/funcionarios/")).andExpect(status().isOk());
	}

	@Test
	public void deveCriarNovoFuncionario_entaoHttpCreated() throws Exception {
		
		FuncionarioDto funcionarioDto = getFuncionarioDtoTeste();
		
		String token = getTestToken();
		
		this.mockMvc.perform(post("/api/funcionarios")
		        .contentType(MediaType.APPLICATION_JSON).header(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token)
		        .content(asJsonString(funcionarioDto)))
		        .andExpect(status().isCreated());
		
	}

	@Test
	public void deveSalvarFuncionarioExistente_entaoHttpCreated() throws Exception {
		
		FuncionarioDto funcionarioDto = getFuncionarioDtoTeste();
		
		String token = getTestToken();
		
		this.mockMvc.perform(put("/api/funcionarios/101")
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token)
		        .content(asJsonString(funcionarioDto)))
		        .andExpect(status().isCreated());
	}

	@Test
	public void deveSalvarFuncionarioExistente_idNaoEncontrado_entaoHttpNotFound() throws Exception {
		
		FuncionarioDto funcionarioDto = getFuncionarioDtoTeste();
		
		String token = getTestToken();
		
		this.mockMvc.perform(put("/api/funcionarios/999")
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token)
		        .content(asJsonString(funcionarioDto)))
		        .andExpect(status().isNotFound());
	}

	@Test
	public void deveObterFuncionarioPorId_entaoHttpOk() throws Exception {
		String token = getTestToken();
		
		this.mockMvc.perform(get("/api/funcionarios/101")
				.header(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token))
				.andExpect(status().isOk());
	}

	@Test
	public void deveObterFuncionarioPorId_idNaoEncontrado_entaoHttpNotFound() throws Exception {
		String token = getTestToken();
		
		this.mockMvc.perform(get("/api/funcionarios/999")
				.header(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token))
				.andExpect(status().isNotFound());
	}

	@Test
	public void deveExcluirFuncionarioPorId_entaoHttpOk() throws Exception {
		String token = getTestToken();
		
		this.mockMvc.perform(delete("/api/funcionarios/101")
				.header(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token))
				.andExpect(status().isOk());
	}

	@Test
	public void deveExcluirFuncionarioPorId_entaoHttpNotFound() throws Exception {
		String token = getTestToken();
		
		this.mockMvc.perform(delete("/api/funcionarios/999")
				.header(JwtSecurityConstants.HEADER_STRING, JwtSecurityConstants.TOKEN_PREFIX + token))
				.andExpect(status().isNotFound());
	}

	private String getTestToken() throws Exception {
		if(testToken == null) {
			testToken = tokenAuthenticationService.createToken(JwtSecurityConstants.USUARIO);
		}
		return testToken;
	}
	
	private FuncionarioDto getFuncionarioDtoTeste() {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setNome("Fulano dos Santos");
		funcionarioDto.setCpf("54311187003");
		funcionarioDto.setDtNascimento(new GregorianCalendar(1999, Calendar.DECEMBER, 31).getTime());
		funcionarioDto.setEndereco("Centro, São Luís-MA");
		funcionarioDto.setContato("98988332211");
		funcionarioDto.setFuncao("Analista de Suporte");
		funcionarioDto.setDepartamento("DEP 05");
		
		return funcionarioDto;
	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
