package com.codecool.shop.model;


/**
 * An instance of this class is an Item in our order. It has a product, quantity and a price.
 */
public class LineItem {

    public Product product;
    protected int quantity;
    protected float totalPrice;

    /**
     * By default, if it gets just a product parameter, quantity is one.
     * @param product Product object which should be added to the LineItem.
     */
    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.totalPrice = product.getDefaultPrice();
    }

    /**
     * A constructor, where you can specify the quantity with the product.
     * @param product Product object which should be added to the LineItem.
     * @param quantity Quantity of the given Product.
     */
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = quantity * product.getDefaultPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Product getProduct() {
        return product;
    }
}
