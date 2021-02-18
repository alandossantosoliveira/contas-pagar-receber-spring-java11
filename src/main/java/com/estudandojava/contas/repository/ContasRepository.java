package com.estudandojava.contas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudandojava.contas.model.Contas;

public interface ContasRepository extends JpaRepository<Contas, Long>{

}
