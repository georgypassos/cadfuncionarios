package com.capgemini.georgy.cadfuncionarios.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Optional<FuncionarioDto> findById(Long id) {
		return Optional.of(FuncionarioMapper.INSTANCE.entityToEntityDto(funcionarioRepository.findById(id).get()));
	}

	@Override
	public FuncionarioDto save(FuncionarioDto funcionarioDto) {
		
		Funcionario funcionario = funcionarioRepository.save(FuncionarioMapper.INSTANCE.entityDtoToEntity(funcionarioDto));
		
		return FuncionarioMapper.INSTANCE.entityToEntityDto(funcionarioRepository.save(funcionario));
	}

	@Override
	public List<FuncionarioDto> findAll() {
		return funcionarioRepository.findAll().stream()
				.map(FuncionarioMapper.INSTANCE::entityToEntityDto)
				.collect(Collectors.toList());
	}
	
	public void deleteById(Long id) {
		funcionarioRepository.deleteById(id);
	}
	
}
