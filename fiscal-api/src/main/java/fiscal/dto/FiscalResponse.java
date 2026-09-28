package fiscal.dto;

public class FiscalResponse {

    private String numeroNota;
    private String chaveAcesso;
    private String status;

    public FiscalResponse(String numeroNota, String chaveAcesso, String status) {
        this.numeroNota = numeroNota;
        this.chaveAcesso = chaveAcesso;
        this.status = status;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public String getStatus() {
        return status;
    }
}