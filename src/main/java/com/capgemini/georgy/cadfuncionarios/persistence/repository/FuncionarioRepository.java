package com.capgemini.georgy.cadfuncionarios.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.georgy.cadfuncionarios.persistence.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
}
