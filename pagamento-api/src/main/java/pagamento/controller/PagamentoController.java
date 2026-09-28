package pagamento.controller;

import pagamento.dto.PagamentoRequest;
import pagamento.dto.PagamentoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
@CrossOrigin(origins = "*")
public class PagamentoController {

    @PostMapping
    public PagamentoResponse processarPagamento(@RequestBody PagamentoRequest request) {
        return new PagamentoResponse(
                "APROVADO",
                UUID.randomUUID().toString()
        );
    }
}