package com.capgemini.georgy.cadfuncionarios.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.georgy.cadfuncionarios.exception.ResourceNotFoundException;
import com.capgemini.georgy.cadfuncionarios.persistence.enums.StatusFuncionario;
import com.capgemini.georgy.cadfuncionarios.persistence.model.Funcionario;
import com.capgemini.georgy.cadfuncionarios.persistence.repository.FuncionarioRepository;
import com.capgemini.georgy.cadfuncionarios.service.IFuncionarioService;
import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;
import com.capgemini.georgy.cadfuncionarios.web.mapper.FuncionarioMapper;

@Service
public class FuncionarioServiceImpl implements IFuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public FuncionarioDto findById(Long id) {
		
		Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);
		
		if(!funcionarioData.isPresent()) {
			throw new ResourceNotFoundException("ID de funcionário não localizado : " + id);
		}
		
		return FuncionarioMapper.INSTANCE.entityToEntityDto(funcionarioData.get());
	}

	@Override
	public FuncionarioDto save(FuncionarioDto funcionarioDto, Long id) {
		
		Funcionario funcionario = new Funcionario();
		
		if(id != null) {
			Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);
			
			if(funcionarioData.isPresent()) {
				funcionario = funcionarioData.get();
			} else {
				throw new ResourceNotFoundException("ID de funcionário não localizado : " + id);
			}
		} else {
			funcionario.setStatus(StatusFuncionario.ATIVO);
		}
		
		funcionario.setNome(funcionarioDto.getNome());
		funcionario.setCpf(funcionarioDto.getCpf());
		funcionario.setDtNascimento(funcionarioDto.getDtNascimento());
		funcionario.setEndereco(funcionarioDto.getEndereco());
		funcionario.setContato(funcionarioDto.getContato());
		funcionario.setFuncao(funcionarioDto.getFuncao());
		funcionario.setDepartamento(funcionarioDto.getDepartamento());
		
		funcionario = funcionarioRepository.save(funcionario);
		
		return FuncionarioMapper.INSTANCE.entityToEntityDto(funcionario);
	}

	@Override
	public List<FuncionarioDto> findAll() {
		return funcionarioRepository.findAll().stream()
				.map(FuncionarioMapper.INSTANCE::entityToEntityDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<FuncionarioDto> findAllAtivos() {
		List<Funcionario> listResult = funcionarioRepository.findByStatus(StatusFuncionario.ATIVO);
		
		if(listResult.isEmpty()) {
			throw new ResourceNotFoundException("Não há funcionários ativos na base");
		}
		
		return listResult.stream()
				.map(FuncionarioMapper.INSTANCE::entityToEntityDto)
				.collect(Collectors.toList());
	}
	
	public void deleteById(Long id) {
		Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);
		
		if(!funcionarioData.isPresent()) {
			throw new ResourceNotFoundException("ID de funcionário não localizado : " + id);
		}
		
		Funcionario funcionario = funcionarioData.get();
		funcionario.setStatus(StatusFuncionario.REMOVIDO);
		
		funcionarioRepository.save(funcionario);
	}
	
}
