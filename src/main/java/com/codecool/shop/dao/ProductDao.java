package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

/**
 * ProductDao is responsible for storing, searching and removing Products.
 */
public interface ProductDao {

    /**
     * Adds a specific Product to the storage.
     * @param product Product object to be stored.
     */
    void add(Product product);

    /**
     * Finds and gives back a Product by id from the storage.
     * @param id the id of the Product object needed.
     * @return Product
     */
    Product find(int id);

    /**
     * Removes a Product by id from the storage.
     * @param id the id of the Product to be removed.
     */
    void remove(int id);

    /**
     * Removes all Products from the storage.
     */
    void removeAll();

    /**
     * Gives back all existing Products from the storage.
     * @return List of Products
     */
    List<Product> getAll();

    /**
     * Gives back all Products of the given Supplier.
     * @param supplier the Supplier which is the base of the filtering.
     * @return List of Products
     */
    List<Product> getBy(Supplier supplier);

    /**
     * Gives back all Products of the given ProductCategory.
     * @param productCategory the ProductCategory which is the base of the filtering.
     * @return List of Products
     */
    List<Product> getBy(ProductCategory productCategory);

}
