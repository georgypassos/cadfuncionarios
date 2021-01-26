package com.capgemini.georgy.cadfuncionarios.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FuncionarioController {

	@Autowired
	private IFuncionarioService funcionarioService;

	@ApiOperation(value = "Retorna todos os funcionários cadastrados (não requer autenticação)")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de funcionários retornada com sucesso"),
			@ApiResponse(code = 204, message = "Não há funcionários cadastrados"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno no servidor") })
	@GetMapping("/public/funcionarios")
	public ResponseEntity<List<FuncionarioDto>> getAllFuncionarios() {
		try {
			List<FuncionarioDto> listResult = funcionarioService.findAll();

			if (listResult.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(listResult, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Busca um funcionário pelo ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Funcionário obtido com sucesso"),
			@ApiResponse(code = 404, message = "ID do registro não localizado")})
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<FuncionarioDto> getFuncionarioById(@PathVariable("id") long id) {
		Optional<FuncionarioDto> funcionarioData = funcionarioService.findById(id);

		if (funcionarioData.isPresent()) {
			return new ResponseEntity<>(funcionarioData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(funcionarioData.orElse(null), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Cria um novo funcionário",
			  notes = "Caso o registro seja criado, retorna dentro do header o atributo \"location\" com o link para o registro. ")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Funcionário criado com sucesso"),
			@ApiResponse(code = 500, message = "Ocorreu um erro interno no servidor")})
	@PostMapping("/funcionarios")
	public ResponseEntity<FuncionarioDto> createFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
		try {
			FuncionarioDto dto = funcionarioService.save(funcionarioDto);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
													  .path("/{id}")
													  .buildAndExpand(dto.getId())
													  .toUri();

			return ResponseEntity.created(location).body(dto);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Atualiza um funcionário existente",
			  	  notes = "Caso o registro seja salvo, retorna dentro do header o atributo \"location\" com o link para o registro. ")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Funcionário salvo com sucesso"),
			@ApiResponse(code = 404, message = "ID do registro não localizado para atualização")})
	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<FuncionarioDto> updateFuncionario(@PathVariable("id") long id, @RequestBody FuncionarioDto funcionarioDto) {
		Optional<FuncionarioDto> funcionarioData = funcionarioService.findById(id);

		if (funcionarioData.isPresent()) {
			FuncionarioDto dto = funcionarioData.get();
			dto.setNome(funcionarioDto.getNome());
			dto.setCpf(funcionarioDto.getCpf());
			dto.setDtNascimento(funcionarioDto.getDtNascimento());
			dto.setEndereco(funcionarioDto.getEndereco());
			dto.setContato(funcionarioDto.getContato());
			dto.setFuncao(funcionarioDto.getFuncao());
			dto.setDepartamento(funcionarioDto.getDepartamento());
			
			dto = funcionarioService.save(dto);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						  .path("/{id}")
						  .buildAndExpand(dto.getId())
						  .toUri();

			return ResponseEntity.created(location).body(dto);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Exclui um funcionário existente")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Funcionário excluído com sucesso"),
		@ApiResponse(code = 500, message = "Ocorreu um erro interno no servidor")})
	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<HttpStatus> deleteFuncionario(@PathVariable("id") long id) {
		try {
			funcionarioService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
