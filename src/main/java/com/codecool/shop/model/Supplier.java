package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * All product must have a supplier which represents a real life supplier.
 */
public class Supplier extends BaseModel {
    private ArrayList<Product> products;

    /**
     * A constructor which just needs a name parameter.
     * @param name Name of the Supplier.
     */
    public Supplier(String name) {
        super(name);
        this.products = new ArrayList<>();
    }

    /**
     * A constructor which needs name and description parameters.
     * @param name Name of the Supplier.
     * @param description Description of the Supplier.
     */
    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }

    /**
     * The overriden equals method looks for the id and name of Suppliers.
     * @param other other Supplier object.
     * @return boolean. If the two Supplier matches, true.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof Supplier)) return false;
        Supplier otherSupplier = (Supplier) other;
        if (this.getId() == otherSupplier.getId() &&
                this.getName().equals(otherSupplier.getName())
                ) {
            return true;
        }
        return false;
    }
}