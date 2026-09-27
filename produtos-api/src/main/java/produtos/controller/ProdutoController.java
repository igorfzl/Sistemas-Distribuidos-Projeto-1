package produtos.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import produtos.dto.ProdutoResponse;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @GetMapping
    public List<ProdutoResponse> listarProdutos() {

        return Arrays.asList(
                new ProdutoResponse(1L, "Mouse Gaming", "Mouse ergonómico com iluminação RGB", 90.99),
                new ProdutoResponse(2L, "Teclado Mecânico", "Teclado mecânico switch azul", 89.90),
                new ProdutoResponse(3L, "Monitor 24 polegadas", "Monitor Full HD 144Hz", 1230.00),
                new ProdutoResponse(4L, "Fone Bluetooth", "Fone com cancelamento de ruído", 120.00)
        );
    }
}