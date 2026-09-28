package email.dto;

import java.time.LocalDateTime;

public class EmailResponse {
    private String status;
    private String mensagem;
    private LocalDateTime dataEnvio;

    public EmailResponse(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.dataEnvio = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }
}