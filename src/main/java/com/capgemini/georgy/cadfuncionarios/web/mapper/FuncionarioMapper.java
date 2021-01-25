package com.capgemini.georgy.cadfuncionarios.web.mapper;

import org.mapstruct.factory.Mappers;

import com.capgemini.georgy.cadfuncionarios.persistence.model.Funcionario;
import com.capgemini.georgy.cadfuncionarios.web.dto.FuncionarioDto;

public interface FuncionarioMapper {
	
	FuncionarioMapper INSTANCE = Mappers.getMapper(FuncionarioMapper.class);

    FuncionarioDto entityToEntityDto(Funcionario funcionario);

    Funcionario entityDtoToEntity(FuncionarioDto funcionarioDto);

}
