package br.com.fiap.universidade_fiap.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TesteController {

	@GetMapping("/teste1")
	public String testar1() {
		return "/testes/teste";
	}

	@GetMapping("/teste2")
	public String testar2(HttpServletRequest req) {
		req.setAttribute("var", "FIAP!!!");
		return "/testes/teste";
	}

	@GetMapping("/teste3")
	public String testar3(Model model) {
		model.addAttribute("var", "ALURA!!!");
		return "/testes/teste";
	}

	@GetMapping("/teste4")
	public ModelAndView testar4() {
		ModelAndView mv = new ModelAndView("/testes/teste");
		mv.addObject("var", "PM3!!!");
		mv.addObject("nome", "Rennan");
		return mv;
	}

}
