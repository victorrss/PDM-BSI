package br.senac.sp.amicao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class Order {
    private UUID id;
    private Integer customerId;
    private List<ItemCart> items = new ArrayList<>();
    private Date createdAt;

    public Order() {
        setId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<ItemCart> getItems() {
        return items;
    }

    public void setItems(List<ItemCart> items) {
        this.items = items;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalPrice(){
        Double sum = 0.0;
        for (ItemCart i: getItems())
            sum = i.getQty() * i.getProduct().getPrecProduto();
        return sum;
    }
}