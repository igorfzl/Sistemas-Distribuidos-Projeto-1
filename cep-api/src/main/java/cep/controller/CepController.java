package cep.controller;

import cep.model.Endereco;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {

    @GetMapping("/{cep}")
    public Endereco buscarCep(@PathVariable String cep) {
        return new Endereco(
                cep,
                "Rua Dom Pedro II",
                "Centro",
                "Toledo",
                "PR"
        );
    }
}