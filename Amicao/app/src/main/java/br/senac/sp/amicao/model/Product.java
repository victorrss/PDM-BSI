package br.senac.sp.amicao.model;

public class Product {
    private String nomeProduto;
    private String descProduto;
    private double precProduto;
    private int idCategoria;
    private int qtdMinEstoque;
    private boolean ativoProduto;
    private int idProduto;
    private double descontoPromocao;

    public Product(int idProduto) {
        this.idProduto = idProduto;
    }

    public Product() {
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public double getPrecProduto() {
        return precProduto;
    }

    public void setPrecProduto(double precProduto) {
        this.precProduto = precProduto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getQtdMinEstoque() {
        return qtdMinEstoque;
    }

    public void setQtdMinEstoque(int qtdMinEstoque) {
        this.qtdMinEstoque = qtdMinEstoque;
    }

    public boolean isAtivoProduto() {
        return ativoProduto;
    }

    public void setAtivoProduto(boolean ativoProduto) {
        this.ativoProduto = ativoProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getDescontoPromocao() {
        return descontoPromocao;
    }

    public void setDescontoPromocao(double descontoPromocao) {
        this.descontoPromocao = descontoPromocao;
    }
}
