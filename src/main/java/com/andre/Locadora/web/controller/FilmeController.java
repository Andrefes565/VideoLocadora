package com.andre.Locadora.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andre.Locadora.models.Filme;
import com.andre.Locadora.repository.FilmeRepository;

@Controller
public class FilmeController {

	@Autowired
	private FilmeRepository fr;

	@RequestMapping(value = "/cadastrarFilme", method = RequestMethod.GET)
	public String form() {
		return "/Filmes/cadastro";
	}

	@RequestMapping(value = "/cadastrarFilme", method = RequestMethod.POST)
	public String form(Filme filme) {
		fr.save(filme);

		return "redirect:/listarFilmes";
	}

	@RequestMapping("/listarFilmes")
	public ModelAndView listaFilmes() {
		ModelAndView mv = new ModelAndView("Filmes/lista");
		Iterable<Filme> filmes = fr.findAll();
		mv.addObject("filmes", filmes);
		return mv;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesFilme(@PathVariable("codigo") long codigo) {
		Filme filme = fr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("/Filmes/detalhesFilme");
		mv.addObject("filme", filme);
		return mv;
	}

	@RequestMapping("/deletarFilme")
	public String deletarFilme(long codigo) {
		Filme filme = fr.findByCodigo(codigo);
		fr.delete(filme);
		return "redirect:/listarFilmes";
	}

	@RequestMapping("/editarFilme")
	public ModelAndView editarFilme(long codigo) {
		Filme filme = fr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("Filmes/editarFilme");
		mv.addObject("filme", fr.findByCodigo(codigo));
		mv.addObject("filme", filme);
		return mv;
	}

	@PostMapping("/alteraFilme")
	public String alteraFilme(Filme filme) {
		this.fr.save(filme);
		return "redirect:/listarFilmes";
	}

	@RequestMapping("/locacaoFilme")
	public ModelAndView locacaoFilme(long codigo) {
		Filme filme = fr.findByCodigo(codigo);
		if(filme.getLocado() == true) {
			 
		}
		ModelAndView mv = new ModelAndView("Filmes/locacaoFilme");
		mv.addObject("filme", fr.findByCodigo(codigo));
		mv.addObject("filme", filme);
		return mv;
	}
	
	@PostMapping("/alteraLocacaoFilme")
	public String alteraLocacaoFilme(Filme filme) {
		filme.setLocado(true);
		this.fr.save(filme);
		return "redirect:/listarFilmes";
	}

}