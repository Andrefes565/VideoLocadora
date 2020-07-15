package com.andre.Locadora.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andre.Locadora.models.Login;
import com.andre.Locadora.repository.LoginRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginRepository lr;
	
	@RequestMapping("/cadastrarLoginGet")
	public String cadastrarLoginGet() {
		return "/Login/CadastroLogin";
	}
	
	@RequestMapping("/cadastrarLoginPost")
	public String cadastrarLoginPost(@RequestParam("email") String email, @RequestParam("senha") Long senha,  RedirectAttributes attr) {
		Login login = new Login(email,senha);
		lr.save(login);
		attr.addFlashAttribute("success", "Cadastro feito com sucesso!");
		return "redirect:/";
	}
	
	
	@RequestMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("senha") Long senha,  RedirectAttributes attr) {
		
		Login login = lr.findByEmail(email);
		
		if( login.getSenha() == senha) {
			attr.addFlashAttribute("success", "Login Realizado com sucesso!");
			return "redirect:/listarFilmes";
		}
		attr.addFlashAttribute("fail", "Email ou senha errados");
		return "redirect:/";
	}
}
