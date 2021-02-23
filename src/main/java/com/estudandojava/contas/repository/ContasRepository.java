package com.estudandojava.contas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudandojava.contas.model.Contas;

public interface ContasRepository extends JpaRepository<Contas, Long>{
	
	List<Contas> findByDescricaoContaining(String descricao);
	List<Contas> findByValor(float valor);
}
