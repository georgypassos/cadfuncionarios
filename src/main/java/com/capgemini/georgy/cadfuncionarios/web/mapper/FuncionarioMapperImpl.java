package com.capgemini.georgy.cadfuncionarios.web.mapper;

import com.capgemini.georgy.cadfuncionarios.persistence.model.Funcionario;
import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;

public class FuncionarioMapperImpl implements FuncionarioMapper {

	@Override
	public Funcionario entityDtoToEntity(FuncionarioDto dto) {
		Funcionario entity = new Funcionario();
		
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setDtNascimento(dto.getDtNascimento());
		entity.setEndereco(dto.getEndereco());
		entity.setContato(dto.getContato());
		entity.setFuncao(dto.getFuncao());
		entity.setDepartamento(dto.getDepartamento());
		
		return entity;
	}

	@Override
	public FuncionarioDto entityToEntityDto(Funcionario entity) {
		FuncionarioDto dto = new FuncionarioDto();
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCpf(entity.getCpf());
		dto.setDtNascimento(entity.getDtNascimento());
		dto.setEndereco(entity.getEndereco());
		dto.setContato(entity.getContato());
		dto.setFuncao(entity.getFuncao());
		dto.setDepartamento(entity.getDepartamento());
		
		return dto;
	}
}
