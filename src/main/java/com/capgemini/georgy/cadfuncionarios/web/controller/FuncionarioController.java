package com.capgemini.georgy.cadfuncionarios.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.georgy.cadfuncionarios.persistence.model.Funcionario;
import com.capgemini.georgy.cadfuncionarios.persistence.repository.FuncionarioRepository;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	//FIXME remover
	@GetMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	@GetMapping("/funcionarios")
	public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();

			funcionarioRepository.findAll().forEach(funcionarios::add);

			if (funcionarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(funcionarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
