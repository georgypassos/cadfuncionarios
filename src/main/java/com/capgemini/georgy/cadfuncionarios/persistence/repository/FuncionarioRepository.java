package com.capgemini.georgy.cadfuncionarios.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.georgy.cadfuncionarios.persistence.enums.StatusFuncionario;
import com.capgemini.georgy.cadfuncionarios.persistence.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	List<Funcionario> findByStatus(StatusFuncionario status);
	
}
