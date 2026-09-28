package entrega.controller;

import entrega.dto.EntregaRequest;
import entrega.dto.EntregaResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/entrega")
@CrossOrigin(origins = "*")
public class EntregaController {

    @PostMapping
    public EntregaResponse registrarEntrega(@RequestBody EntregaRequest request) {
        return new EntregaResponse(
                UUID.randomUUID().toString(),
                "AGUARDANDO_COLETA",
                5
        );
    }
}