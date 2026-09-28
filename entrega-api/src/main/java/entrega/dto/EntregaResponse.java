package entrega.dto;

public class EntregaResponse {

    private String codigoRastreio;
    private String status;
    private Integer diasEstimados;

    public EntregaResponse(String codigoRastreio, String status, Integer diasEstimados) {
        this.codigoRastreio = codigoRastreio;
        this.status = status;
        this.diasEstimados = diasEstimados;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public String getStatus() {
        return status;
    }

    public Integer getDiasEstimados() {
        return diasEstimados;
    }
}