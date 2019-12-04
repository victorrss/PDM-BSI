package br.senac.sp.amicao.model;

import java.util.ArrayList;
import java.util.List;

public final class Cart {
    private static Cart INSTANCE;
    private static List<ItemCart> items = new ArrayList<>();

    private Cart() {
    }

    public static synchronized Cart getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Cart();
        return INSTANCE;
    }

    public void add(ItemCart item) throws Exception {
        if (existsInCart(item))
            throw new Exception("Este produto já foi adicionado no carrinho!");

        items.add(item);
    }

    public void delete(ItemCart item) {
        items.remove(getItem(item));
    }

    public void updateQuantity(ItemCart i) throws Exception {
        if (i.getQty() == null || i.getQty() < 1)
            throw new Exception("Quantidade inválida");

        ItemCart update = getItem(i);
        update.setQty(i.getQty());
    }

    public ItemCart getItem(ItemCart item) {
        for (ItemCart itemCart : items)
            if (itemCart.getProduct().getIdProduto() == item.getProduct().getIdProduto())
                return itemCart;
        return null;

    }

    public Double getTotalPrice() {
        Double sum = 0.0;
        for (ItemCart item : items)
            sum += item.getQty() * item.getProduct().getPrecProduto();

        return sum;
    }

    private boolean existsInCart(ItemCart item) {
        for (ItemCart itemCart : items)
            if (itemCart.getProduct().getIdProduto() == item.getProduct().getIdProduto())
                return true;
        return false;
    }

    public List<ItemCart> getItems() {
        return items;
    }
}