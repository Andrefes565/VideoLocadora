package com.andre.Locadora.web.controller;


import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String form(Filme filme, RedirectAttributes attr) {
		fr.save(filme);
		attr.addFlashAttribute("success", "Filme Cadastrado com sucesso!");
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
	public String deletarFilme(long codigo, RedirectAttributes attr) {
		Filme filme = fr.findByCodigo(codigo);
		fr.delete(filme);
		attr.addFlashAttribute("success", "Filme Excluído com sucesso!");
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
	public String alteraFilme(Filme filme, RedirectAttributes attr) {
		this.fr.save(filme);
		attr.addFlashAttribute("success", "Filme Alterado com sucesso!");
		return "redirect:/listarFilmes";
	}

	@RequestMapping("/locacaoFilme")
	public ModelAndView locacaoFilme(long codigo, ModelMap model) {
		Filme filme = fr.findByCodigo(codigo);
		if(filme.getLocado() == true) {
			model.addAttribute("fail", "O Filme Já foi alugado!");
			return new ModelAndView("Filmes/lista", model);
		}
		ModelAndView mv = new ModelAndView("Filmes/locacaoFilme");
		mv.addObject("filme", fr.findByCodigo(codigo));
		mv.addObject("filme", filme);
		return mv;
	}
	
	@PostMapping("/alteraLocacaoFilme")
	public String alteraLocacaoFilme(Filme filme, RedirectAttributes attr) {
		attr.addFlashAttribute("success", "Filme Alugado com sucesso!");
		filme.setLocado(true);
		this.fr.save(filme);
		return "redirect:/listarFilmes";
	}
	
	@RequestMapping("/DarBaixaFilme")
	public String DarBaixaFilme(long codigo, RedirectAttributes attr) {
		attr.addFlashAttribute("success", "Devolução feita com sucesso!");
		Filme filme = fr.findByCodigo(codigo);
		filme.setLocado(false);
		this.fr.save(filme);
		return "redirect:/listarFilmes";
	}
	
	
	@RequestMapping("/consultarFilme")
	public ModelAndView consultarFilme(@RequestParam("nomeFilme") String nomeFilme) {
		Filme filme = fr.findByFilme(nomeFilme);
		ModelAndView mv = new ModelAndView("Filmes/lista");
		mv.addObject("filmes", filme);
		return mv;
	}
	/*
	@RequestMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("senha") Long senha,  RedirectAttributes attr) {
		
		if ((email.equals("andre@gmail.com") && (senha == 1234))) {
			
			return "redirect:/listarFilmes";
		}
		attr.addFlashAttribute("fail", "Email ou senha errados");
		return "redirect:/";
	}
	*/

}
