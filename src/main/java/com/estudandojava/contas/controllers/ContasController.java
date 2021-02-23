package com.estudandojava.contas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.estudandojava.contas.model.Contas;
import com.estudandojava.contas.services.ContasService;

@Controller
@RequestMapping("/contas")
public class ContasController {

	@Autowired
	private ContasService contasService;
	
	@RequestMapping("/listar")
	public String index(@RequestParam(value = "search", required = false) String q, Model model) {
		
		List<Contas> list = null;
		
		if(q != null && (!q.matches("[-+]?[0-9]*\\.?[0-9]+")) ) {
			list = contasService.listAllLikeDescricao(q);
			model.addAttribute("search", q);			
		}else if (q != null && (q.matches("[-+]?[0-9]*\\.?[0-9]+")) ){
			list = contasService.listAllByValor(Float.parseFloat(q) );
			model.addAttribute("search", q);			
		}else {
			list = contasService.listAll();
			model.addAttribute("search", "");
		}	
		
		model.addAttribute("listaContas", list);			
		return "index";
	}
	
	@RequestMapping("/adicionar")
	public ModelAndView add(Contas conta) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adicionar");
		mv.addObject("conta", conta);
		
		return mv;		
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		contasService.delete(id);
		
		return new ModelAndView("redirect:/contas/listar");
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		
		return add(contasService.get(id));
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("conta") Contas conta, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(conta);
		}
		contasService.save(conta);
		
		ModelAndView mv = new ModelAndView("redirect:/contas/listar?add=sim");
		
		return mv;
	}
}
