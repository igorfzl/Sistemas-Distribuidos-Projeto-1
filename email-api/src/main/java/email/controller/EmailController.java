package email.controller;

import email.dto.EmailRequest;
import email.dto.EmailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailController {
    
    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @PostMapping("/enviar")
    public EmailResponse enviarEmail(@RequestBody EmailRequest request) {

        log.info("Processando envio de e-mail simulado...");
        log.info("Destinatário: {}", request.getDestinatario());
        log.info("Assunto: {}", request.getAssunto());

        return new EmailResponse(
                "SUCESSO",
                "O e-mail foi processado e enviado (simulação) com sucesso."
        );
    }
}