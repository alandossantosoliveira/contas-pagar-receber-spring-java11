package com.estudandojava.contas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.estudandojava.contas.model.Contas;
import com.estudandojava.contas.services.ContasService;

@Controller
@RequestMapping("/contas")
public class ContasController {

	@Autowired
	private ContasService contasService;
	
	@RequestMapping("/listar")
	public String index(Model model) {
		List<Contas> list = contasService.listAll();
		model.addAttribute("listaContas", list);
		
		return "index";
	}
	
	@RequestMapping("/adicionar")
	public ModelAndView add(Contas conta) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adicionar");
		mv.addObject(conta);
		
		return mv;		
	}
}
