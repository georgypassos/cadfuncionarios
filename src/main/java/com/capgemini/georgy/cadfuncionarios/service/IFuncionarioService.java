package com.capgemini.georgy.cadfuncionarios.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;

public interface IFuncionarioService {

	Optional<FuncionarioDto> findById(Long id);

	FuncionarioDto save(FuncionarioDto funcionarioDto);

	List<FuncionarioDto> findAll();

	void deleteById(Long id);
	
}
