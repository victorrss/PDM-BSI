package br.senac.sp.amicao.service;

import java.util.ArrayList;
import java.util.List;

import br.senac.sp.amicao.model.Order;
import br.senac.sp.amicao.model.Order;

public final class OrderManager {
    private static OrderManager INSTANCE;
    private static List<Order> orders = new ArrayList<>();

    private OrderManager() {
    }

    public static synchronized OrderManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new OrderManager();
        return INSTANCE;
    }

    public void add(Order order){
        orders.add(order);
    }

    public void delete(Order item) {
        orders.remove(item);
    }

    public List<Order> getItems() {
        return orders;
    }
}