package fiscal.controller;

import fiscal.dto.FiscalRequest;
import fiscal.dto.FiscalResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Random;

@RestController
@RequestMapping("/fiscal")
@CrossOrigin(origins = "*")
public class FiscalController {

    @PostMapping
    public FiscalResponse gerarNotaFiscal(@RequestBody FiscalRequest request) {
        String chaveFicticia = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String numeroFicticio = String.format("%09d", new Random().nextInt(1000000000));

        return new FiscalResponse(
                numeroFicticio,
                chaveFicticia,
                "AUTORIZADA"
        );
    }
}