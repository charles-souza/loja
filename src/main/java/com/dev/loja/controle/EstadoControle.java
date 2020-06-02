package com.dev.loja.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.modelos.EntradaItens;
import com.dev.loja.modelos.EntradaProduto;
import com.dev.loja.modelos.Estado;
import com.dev.loja.repositorios.EntradaItensRepositorio;
import com.dev.loja.repositorios.EntradaProdutoRepositorio;
import com.dev.loja.repositorios.EstadoRepositorio;
import com.dev.loja.repositorios.FuncionarioRepositorio;
import com.dev.loja.repositorios.ProdutoRepositorio;

@Controller
public class EstadoControle {

	private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>();

	@Autowired
	private EntradaProdutoRepositorio entradaProdutoRepositorio;

	@Autowired
	private EntradaItensRepositorio entradaItensRepositorio;

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@GetMapping("/administrativo/entrada/cadastrar")
	public ModelAndView cadastrar(EntradaProduto entrada, EntradaItens entradaItens) {
		ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
		mv.addObject("entrada", entrada);
		mv.addObject("listaEntradaItens", this.listaEntrada);
		mv.addObject("entradaItens", entradaItens);
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		mv.addObject("listaProdutos", produtoRepositorio.findAll());
		return mv;
	}

//	@GetMapping("/administrativo/estados/listar")
//	public ModelAndView listar() {
//		ModelAndView mv = new ModelAndView("administrativo/estados/lista");
//		mv.addObject("listaEstados", entradaProdutoRepositorio.findAll(Sort.by(Sort.Direction.ASC, "id")));
//		return mv;
//	}

//	@GetMapping("/administrativo/estados/editar/{id}")
//	public ModelAndView editar(@PathVariable("id") Long id) {
//		Optional<Estado> estado = entradaProdutoRepositorio.findById(id);
//		return cadastrar(estado.get());
//		
//	}

//	@GetMapping("/administrativo/estados/remover/{id}")
//	public ModelAndView remover(@PathVariable("id") Long id) {
//		Optional<Estado> estado = entradaProdutoRepositorio.findById(id);
//		entradaProdutoRepositorio.delete(estado.get());
//		return listar();
//		
//	}	

	@PostMapping("/administrativo/entrada/salvar")
	public ModelAndView salvar(String acao, EntradaProduto entrada, List<EntradaItens> listaEntrada,
			EntradaItens entradaItens) {

		if (acao.contentEquals("itens")) {
			this.listaEntrada.add(entradaItens);
		}

		System.out.println(listaEntrada.size());

		return cadastrar(entrada, new EntradaItens());
	}

}
