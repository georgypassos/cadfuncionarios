package com.capgemini.georgy.cadfuncionarios.cadfuncionarios.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.georgy.cadfuncionarios.exception.ResourceNotFoundException;
import com.capgemini.georgy.cadfuncionarios.persistence.enums.StatusFuncionario;
import com.capgemini.georgy.cadfuncionarios.service.IFuncionarioService;
import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;

@SpringBootTest
public class FuncionarioServiceTests {

	@Autowired
	private IFuncionarioService funcionarioService;
	
	@Test
	public void deveObterFuncionarioPorId_entaoRetornarID() {
		FuncionarioDto funcionarioDto = funcionarioService.findById(101L);
		assertThat(funcionarioDto.getId()).isNotNull();
	}

	@Test
	public void deveObterFuncionarioPorId_idNaoEncontrado_entaoResourceNotFoundException() {
		assertThrows(ResourceNotFoundException.class, () -> {
			funcionarioService.findById(9L);
		});
	}

	@Test
	public void deveObterFuncionariosAtivos_entaoOk() {
		List<FuncionarioDto> listResult = funcionarioService.findAllAtivos();
		assertThat(listResult).isNotEmpty();
	}

	@Test
	public void deveCriarUmFuncionario_entaoRetornarID() {
		FuncionarioDto funcionarioDto = getFuncionarioDtoTeste();
		
		funcionarioDto = funcionarioService.save(funcionarioDto, null);
		
		assertThat(funcionarioDto.getId()).isNotNull();
	}

	@Test
	public void deveExcluirFuncionarioPorId_entaoStatusRemovido() {
		FuncionarioDto funcionarioDto = funcionarioService.findById(101L);
		
		funcionarioService.deleteById(101L);
		
		funcionarioDto = funcionarioService.findById(101L);
		
		assertThat(funcionarioDto.getStatus().equals(StatusFuncionario.REMOVIDO.toString())).isTrue();
	}

	@Test
	public void deveExcluirFuncionarioPorId_idNaoEncontrado_entaoResourceNotFoundException() {
		assertThrows(ResourceNotFoundException.class, () -> {
			funcionarioService.findById(9L);
		});
	}
	
	@Test
	public void deveSalvarUmFuncionario_idNaoEncontrado_entaoResourceNotFoundException() {
		FuncionarioDto funcionarioDto = getFuncionarioDtoTeste();
		
		assertThrows(ResourceNotFoundException.class, () -> {
			funcionarioService.save(funcionarioDto, 9L);
		});
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
	
}
