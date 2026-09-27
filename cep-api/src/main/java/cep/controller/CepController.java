package cep.controller;

import cep.dto.EnderecoResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cep")
public class CepController {

    @GetMapping("/{cep}")
    public EnderecoResponse buscarCep(@PathVariable String cep) {
        return new EnderecoResponse(cep, "Rua Dom Pedro II", "Centro", "Toledo", "PR");
    }
}