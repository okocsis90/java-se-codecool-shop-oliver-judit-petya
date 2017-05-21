package com.codecool.shop.dao.memImplementation;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private static OrderDaoMem instance = null;
    private List<Order> DATA = new ArrayList<>();

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public List<Order> getAll() {
        return DATA;
    }

    @Override
    public void add(Order order) {
        order.setId(DATA.size() + 1);
        DATA.add(order);
        logger.info("Order no. {} added to our mem. ", order.getId());
    }

    @Override
    public Order find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        logger.info("Order no. {} will be removed from mem.", find(id).getId());
        DATA.remove(find(id));
    }
}
