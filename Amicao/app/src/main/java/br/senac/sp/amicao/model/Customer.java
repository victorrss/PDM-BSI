package br.senac.sp.amicao.model;

public class Customer {
    private String emailCliente;
    private String senhaCliente;
    private String CPFCliente;
    private String celularCliente;
    private String telComercialCliente;
    private String telResidencialCliente;
    private String nomeCompletoCliente;
    private Integer idCliente;
    private String dtNascCliente;
    private Boolean recebeNewsLetter;

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public String getCPFCliente() {
        return CPFCliente;
    }

    public void setCPFCliente(String CPFCliente) {
        this.CPFCliente = CPFCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getTelComercialCliente() {
        return telComercialCliente;
    }

    public void setTelComercialCliente(String telComercialCliente) {
        this.telComercialCliente = telComercialCliente;
    }

    public String getTelResidencialCliente() {
        return telResidencialCliente;
    }

    public void setTelResidencialCliente(String telResidencialCliente) {
        this.telResidencialCliente = telResidencialCliente;
    }

    public String getNomeCompletoCliente() {
        return nomeCompletoCliente;
    }

    public void setNomeCompletoCliente(String nomeCompletoCliente) {
        this.nomeCompletoCliente = nomeCompletoCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDtNascCliente() {
        return dtNascCliente;
    }

    public void setDtNascCliente(String dtNascCliente) {
        this.dtNascCliente = dtNascCliente;
    }

    public Boolean getRecebeNewsLetter() {
        return recebeNewsLetter;
    }

    public void setRecebeNewsLetter(Boolean recebeNewsLetter) {
        this.recebeNewsLetter = recebeNewsLetter;
    }
}
