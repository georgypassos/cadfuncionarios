package com.capgemini.georgy.cadfuncionarios.web.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Detalhes de cadastro do funcionário")
public class FuncionarioDto {
	
	@JsonView(Views.Publico.class)
	@ApiModelProperty(value = "ID único gerado pelo serviço")
	private Long id;
	
	@JsonView(Views.Publico.class)
	@ApiModelProperty(value = "Nome do funcionário")
	private String nome;
	
	@JsonView(Views.Interno.class)
	@ApiModelProperty(value = "CPF (apenas números)")
	private String cpf;
	
	@JsonView(Views.Publico.class)
	@ApiModelProperty(value = "Data de Nascimento")
	private Date dtNascimento;
	
	@JsonView(Views.Interno.class)
	@ApiModelProperty(value = "Endereço")
	private String endereco;
	
	@JsonView(Views.Interno.class)
	@ApiModelProperty(value = "Contato")
	private String contato;
	
	@JsonView(Views.Publico.class)
	@ApiModelProperty(value = "Cargo do funcionário na empresa")
	private String funcao;
	
	@JsonView(Views.Publico.class)
	@ApiModelProperty(value = "Departamento que ele está alocado")
	private String departamento;
	
	@JsonView(Views.Publico.class)
	@ApiModelProperty(value = "Status do funcionário (ATIVO, REMOVIDO)")
	private String status;
	
	public FuncionarioDto() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
