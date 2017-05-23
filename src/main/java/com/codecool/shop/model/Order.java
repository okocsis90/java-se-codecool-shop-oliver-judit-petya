package com.codecool.shop.model;


import com.codecool.shop.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of order with its unique id is generated with the session. If you click on add to cart button,
 * the given product with the specified quantity will be given to the order instances "items" list.
 * It then updates the orderPrice and orderQuantity values.
 */
public class Order {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private List<LineItem> items = new ArrayList<>();
    private float orderPrice;
    private int orderQuantity;
    private int id;


    /**
     * This method adds a LineItem to the specific order. If the LineItem is already in the order
     * it just updates it. Then it also updates orderPrice and orderQuantity.
     * @param item LineItem object to be added to the order.
     */
    public void addLineItem(LineItem item) {
        int counter = 0;
        logger.info("current items in order: ");
        for (LineItem n : items) {
            logger.info("product: {}, quantity: {}, price: {}", n.getProduct().getName(), n.getQuantity(), n.getTotalPrice());
            if (n.product.equals(item.product)) {
                n.quantity += item.quantity;
                n.totalPrice += item.totalPrice;
                counter += 1;
                logger.info("This product will be updated as follows: current quantity: {}, current price: {}", n.getQuantity(), n.getTotalPrice());
            }
        }
        if (counter == 0) {
            items.add(item);
            logger.info("New Item in order: Product: {}, quantity: {}, price: {}", item.getProduct().getName(), item.getQuantity(), item.getTotalPrice());
        }
        updateOrderPrice(item);
        updateOrderQuantity(item);
        logger.info("The sum of all products in order: {}, total price: {}", this.getOrderQuantity(), this.getOrderPrice());
    }

    public void updateOrderPrice(LineItem item) {
        this.orderPrice += item.totalPrice;
    }

    public void updateOrderQuantity(LineItem item) {
        this.orderQuantity += item.quantity;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
