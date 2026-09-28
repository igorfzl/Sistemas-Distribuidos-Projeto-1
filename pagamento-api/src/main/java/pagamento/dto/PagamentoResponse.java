package pagamento.dto;

public class PagamentoResponse {

    private String status;
    private String codigoTransacao;

    public PagamentoResponse(String status, String codigoTransacao) {
        this.status = status;
        this.codigoTransacao = codigoTransacao;
    }

    public String getStatus() {
        return status;
    }

    public String getCodigoTransacao() {
        return codigoTransacao;
    }
}