package com.estudandojava.contas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudandojava.contas.model.Contas;
import com.estudandojava.contas.repository.ContasRepository;

@Service
@Transactional
public class ContasService {
	
	@Autowired
	private ContasRepository rep;
	
	public List<Contas> listAll(){
		return rep.findAll();
	}
	
	public List<Contas> listAllLikeDescricao(String descricao){
		return rep.findByDescricaoContaining(descricao);
	}
	
	public List<Contas> listAllByValor(Float valor){
		return rep.findByValor(valor);
	}
	
	public void save(Contas conta) {
		rep.save(conta);
	}
	public Contas get(Long id) {
		return rep.findById(id).get();
	}
	public void delete(Long id) {
		rep.deleteById(id);
	}
	public ContasService() {
		// TODO Auto-generated constructor stub
	}
}
