package com.capgemini.georgy.cadfuncionarios.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.georgy.cadfuncionarios.service.IFuncionarioService;
import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;
import com.capgemini.georgy.cadfuncionarios.web.dto.Views;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin
@Api(tags = "Funcionarios")
@RequestMapping("/api")
@RestController
public class FuncionarioController {

	@Autowired
	private IFuncionarioService funcionarioService;

	@JsonView(Views.Publico.class)
	@ApiOperation(value = "Retorna todos os funcionários cadastrados (não requer autenticação)")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de funcionários retornada com sucesso"),
			@ApiResponse(code = 204, message = "Não há funcionários cadastrados")})
	@GetMapping("/public/funcionarios")
	public ResponseEntity<List<FuncionarioDto>> getAllFuncionarios() {
		
		return ResponseEntity.ok(funcionarioService.findAllAtivos());
	}
	
	@JsonView(Views.Interno.class)
	@ApiOperation(value = "Busca um funcionário pelo ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Funcionário obtido com sucesso"),
			@ApiResponse(code = 404, message = "ID do registro não localizado")})
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<FuncionarioDto> getFuncionarioById(@PathVariable("id") long id) {
		
		return ResponseEntity.ok(funcionarioService.findById(id));
	}

	@JsonView(Views.Interno.class)
	@ApiOperation(value = "Cria um novo funcionário",
			  notes = "Caso o registro seja criado, retorna dentro do header o atributo \"location\" com o link para o registro. ")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Funcionário criado com sucesso"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno no servidor")})
	@PostMapping("/funcionarios")
	public ResponseEntity<FuncionarioDto> createFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
		FuncionarioDto dto = funcionarioService.save(funcionarioDto, null);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												  .path("/{id}")
												  .buildAndExpand(dto.getId())
												  .toUri();

		return ResponseEntity.created(location).body(dto);
	}

	@JsonView(Views.Interno.class)
	@ApiOperation(value = "Atualiza um funcionário existente",
			  	  notes = "Caso o registro seja salvo, retorna dentro do header o atributo \"location\" com o link para o registro. ")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Funcionário salvo com sucesso"),
			@ApiResponse(code = 404, message = "ID do registro não localizado para atualização")})
	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<FuncionarioDto> updateFuncionario(@PathVariable("id") long id, @RequestBody FuncionarioDto funcionarioDto) {
		funcionarioDto = funcionarioService.save(funcionarioDto, id);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					  .path("/{id}")
					  .buildAndExpand(id)
					  .toUri();

		return ResponseEntity.created(location).body(funcionarioDto);
	}

	@JsonView(Views.Interno.class)
	@ApiOperation(value = "Exclui um funcionário existente")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Funcionário excluído com sucesso"),
		@ApiResponse(code = 404, message = "ID do registro não localizado para exclusão")})
	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<HttpStatus> deleteFuncionario(@PathVariable("id") long id) {
		funcionarioService.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	
}
