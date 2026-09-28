package loja.service;

import loja.dto.CompraFormDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrquestradorService {

    private static final Logger log = LoggerFactory.getLogger(OrquestradorService.class);
    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> processarFluxoDeCompra(CompraFormDTO form) {
        String idPedido = UUID.randomUUID().toString();
        List<String> relatorioTela = new ArrayList<>();

        log.info("=== INICIANDO ORQUESTRAÇÃO DE COMPRA (Pedido: {}) ===", idPedido);
        relatorioTela.add("Iniciando orquestração para o pedido: " + idPedido);

        try {
            log.info("Passo 6: Acionando Email Web API (8083) para confirmação da compra...");
            enviarEmail("cliente@teste.com", "Confirmação de Compra", "Recebemos o pedido " + idPedido, relatorioTela);

            log.info("Passo 7: Acionando Pagamento Web API (8086)...");
            Map<String, Object> pagamentoRequest = new HashMap<>();
            pagamentoRequest.put("numeroCartao", form.getNumeroCartao());
            pagamentoRequest.put("valorTotal", 250.00);
            Map pagamentoResponse = restTemplate.postForObject("http://localhost:8086/pagamento", pagamentoRequest, Map.class);
            log.info("Retorno Pagamento API: {}", pagamentoResponse);
            relatorioTela.add("✅ Pagamento API (8086): Transação processada.");

            log.info("Passo 8: Acionando Email Web API (8083) com resultado do pagamento...");
            enviarEmail("cliente@teste.com", "Pagamento Aprovado", "O pagamento do pedido " + idPedido + " foi aprovado.", relatorioTela);

            log.info("Passo 9: Acionando Fiscal Web API (8085)...");
            Map<String, Object> fiscalRequest = new HashMap<>();
            fiscalRequest.put("idPedido", idPedido);
            fiscalRequest.put("valorTotal", 250.00);
            Map fiscalResponse = restTemplate.postForObject("http://localhost:8085/fiscal", fiscalRequest, Map.class);
            log.info("Retorno Fiscal API: {}", fiscalResponse);
            relatorioTela.add("✅ Fiscal API (8085): Nota fiscal gerada e estoque atualizado.");

            log.info("Passo 10: Acionando Email Web API (8083) com a nota fiscal...");
            enviarEmail("cliente@teste.com", "Nota Fiscal Gerada", "A nota fiscal do pedido " + idPedido + " encontra-se em anexo.", relatorioTela);

            log.info("Passo 11: Acionando Entrega Web API (8084)...");
            Map<String, Object> entregaRequest = new HashMap<>();
            entregaRequest.put("idPedido", idPedido);
            entregaRequest.put("cepDestino", form.getCep());
            Map entregaResponse = restTemplate.postForObject("http://localhost:8084/entrega", entregaRequest, Map.class);
            log.info("Retorno Entrega API: {}", entregaResponse);
            relatorioTela.add("✅ Entrega API (8084): Produtos disponibilizados para expedição.");

            log.info("Passo 12: Acionando Email Web API (8083) com dados da entrega...");
            enviarEmail("cliente@teste.com", "Pedido Enviado", "A sua encomenda foi entregue à transportadora.", relatorioTela);

            log.info("=== ORQUESTRAÇÃO CONCLUÍDA COM SUCESSO ===");
            relatorioTela.add("🎉 O fluxo de 12 passos foi concluído com sucesso!");

        } catch (Exception e) {
            log.error("Erro na orquestração: {}", e.getMessage());
            relatorioTela.add("❌ Erro de comunicação com alguma API: " + e.getMessage());
        }
        return relatorioTela;
    }

    private void enviarEmail(String destinatario, String assunto, String corpo, List<String> relatorioTela) {
        Map<String, String> emailRequest = new HashMap<>();
        emailRequest.put("destinatario", destinatario);
        emailRequest.put("assunto", assunto);
        emailRequest.put("corpoMensagem", corpo);

        try {
            Map resposta = restTemplate.postForObject("http://localhost:8083/email/enviar", emailRequest, Map.class);
            log.info("Retorno Email Web API: {}", resposta);
            relatorioTela.add("✅ Email API (8083): E-mail '" + assunto + "' enviado.");
        } catch (Exception e) {
            log.error("A API de E-mail pode estar desligada. Erro: {}", e.getMessage());
            relatorioTela.add("⚠️ Email API (8083): Falha de comunicação.");
        }
    }
}