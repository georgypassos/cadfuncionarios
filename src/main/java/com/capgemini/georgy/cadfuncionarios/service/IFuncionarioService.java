package com.capgemini.georgy.cadfuncionarios.service;

import java.util.List;

import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;

public interface IFuncionarioService {

	FuncionarioDto findById(Long id);

	FuncionarioDto save(FuncionarioDto funcionarioDto, Long id);

	List<FuncionarioDto> findAll();

	List<FuncionarioDto> findAllAtivos();

	void deleteById(Long id);

}
