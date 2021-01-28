package com.capgemini.georgy.cadfuncionarios.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.georgy.cadfuncionarios.persistence.model.Funcionario;
import com.capgemini.georgy.cadfuncionarios.persistence.repository.FuncionarioRepository;

@SpringBootTest
public class FuncionarioRepositoryTests {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Test
	public void deveCriarUmFuncionario_entaoRetornarID() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fulano da Silva");
		funcionario.setCpf("54311187003");
		funcionario.setDtNascimento(new GregorianCalendar(1988, Calendar.MAY, 21).getTime());
		funcionario.setEndereco("Cohatrac, São Luís-MA");
		funcionario.setContato("98988776655");
		funcionario.setFuncao("Arquiteto de Software");
		funcionario.setDepartamento("DEP 03");
		
		Funcionario newFuncionario = funcionarioRepository.save(funcionario);
		
		assertTrue(newFuncionario.getId() != null);
	}

}
