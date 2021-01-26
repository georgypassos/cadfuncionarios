package com.capgemini.georgy.cadfuncionarios.cadfuncionarios.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.georgy.cadfuncionarios.service.IFuncionarioService;
import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;

@SpringBootTest
public class FuncionarioServiceTests {

	@Autowired
	private IFuncionarioService funcionarioService;
	
	@Test
	public void deveCriarUmFuncionario_entaoRetornarID() {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setNome("Fulano da Silva");
		funcionarioDto.setCpf("54311187003");
		funcionarioDto.setDtNascimento(new GregorianCalendar(1988, Calendar.MAY, 21).getTime());
		funcionarioDto.setEndereco("Cohatrac, São Luís-MA");
		funcionarioDto.setContato("98988776655");
		funcionarioDto.setFuncao("Arquiteto de Software");
		funcionarioDto.setDepartamento("DEP 03");
		
		funcionarioDto = funcionarioService.save(funcionarioDto, null);
		
		assertThat(funcionarioDto.getId()).isNotNull();
	}
	
}
