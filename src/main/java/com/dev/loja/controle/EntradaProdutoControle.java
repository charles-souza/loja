package com.dev.loja.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.modelos.Estado;
import com.dev.loja.repositorios.EstadoRepositorio;

@Controller
public class EntradaProdutoControle {
	
	@Autowired
	private EstadoRepositorio estadosRepositorio;
	
	@GetMapping("/administrativo/estados/cadastrar")
	public ModelAndView cadastrar(Estado estado) {
		ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
		mv.addObject("estado", estado);
		return mv;
	}
	
	@GetMapping("/administrativo/estados/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/estados/lista");
		mv.addObject("listaEstados", estadosRepositorio.findAll(Sort.by(Sort.Direction.ASC, "id")));
		return mv;
	}
	
	@GetMapping("/administrativo/estados/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadosRepositorio.findById(id);
		return cadastrar(estado.get());
		
	}
	
	@GetMapping("/administrativo/estados/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadosRepositorio.findById(id);
		estadosRepositorio.delete(estado.get());
		return listar();
		
	}	
	
	@PostMapping("/administrativo/estados/salvar")
	public ModelAndView salvar(Estado estado, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(estado);
		}
		estadosRepositorio.saveAndFlush(estado);
		return listar();
	}

}
