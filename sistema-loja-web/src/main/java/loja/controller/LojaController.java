package loja.controller;

import loja.dto.CompraFormDTO;
import loja.service.OrquestradorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class LojaController {

    private final OrquestradorService orquestradorService;
    private final RestTemplate restTemplate = new RestTemplate();

    public LojaController(OrquestradorService orquestradorService) {
        this.orquestradorService = orquestradorService;
    }

    @GetMapping
    public String exibirLoja(Model model) {
        List produtos = restTemplate.getForObject("http://localhost:8082/produtos", List.class);
        model.addAttribute("produtos", produtos);
        model.addAttribute("compraForm", new CompraFormDTO());

        return "index";
    }

    @PostMapping("/comprar")
    public String processarCompra(CompraFormDTO compraForm, Model model) {
        List<String> resultadosOrquestracao = orquestradorService.processarFluxoDeCompra(compraForm);
        model.addAttribute("resultados", resultadosOrquestracao);

        return "sucesso";
    }
}