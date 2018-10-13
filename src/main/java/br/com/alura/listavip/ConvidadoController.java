package br.com.alura.listavip;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.enviadorEmail.EmailService;
import br.com.alura.listavip.dao.ConvidadoRepository;
import br.com.alura.listavip.model.Convidado;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoRepository repository;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/listaconvidados")
	public ModelAndView listaConvidados(Model model) {

		Iterable<Convidado> convidados = repository.findAll();

		ModelAndView mv = new ModelAndView("listaDeConvidados");
		model.addAttribute("convidados", convidados);

		return mv;
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Convidado convidado) {

		EmailService email =new EmailService();
		email.enviar(convidado.getNome(), convidado.getEmail());
		
		repository.save(convidado);

		return "redirect:listaconvidados";
	}
	
	@PostMapping("/deletar")
	public String deleta (@Valid Convidado convidado) {
		
		repository.delete(convidado);
		
		return "redirect:listaconvidados";
	}

}
