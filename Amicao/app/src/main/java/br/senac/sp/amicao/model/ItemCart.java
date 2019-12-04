package br.senac.sp.amicao.model;

public class ItemCart {
    private Product product;
    private Integer qty = 1;

    public ItemCart() { }

    public ItemCart(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty == null || qty == 0 ? 1 : qty;
    }
}
