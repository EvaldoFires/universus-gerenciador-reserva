package br.com.universus.gerenciador_reserva.domain.models;

import br.com.universus.gerenciador_reserva.infra.exceptions.CRMForaDoPadraoException;

public class Medico {

    private String crm;
    private String nome;

    public Medico(String crm, String nome) {
        validarCRM(crm);

        this.crm = crm;
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private void validarCRM(String crm) {
        if (crm == null || crm.trim().isEmpty()) {
            throw new CRMForaDoPadraoException("CRM não pode ser nulo ou vazio!");
        }

        String crmLimpo = crm.trim();
        if (!crmLimpo.matches("^\\d{5}-\\d/[A-Z]{2}$")) {
            throw new CRMForaDoPadraoException("CRM fora do padrão. " +
                    "Formato esperado: XXXXX-X/UF (e.g., 12345-6/SP)");
        }
    }
}
