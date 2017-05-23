package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * ProductCategoryDao is responsible for storing, searching and removing ProductCategories.
 */
public interface ProductCategoryDao {

    /**
     * Adds a specific ProductCategory to the storage.
     * @param category a ProductCategory object to be stored.
     */
    void add(ProductCategory category);

    /**
     * Finds and gives back a ProductCategory by id from the storage.
     * @param id the id of the ProductCategory needed.
     * @return ProductCategory
     */
    ProductCategory find(int id);

    /**
     * Removes a ProductCategory by id from the storage.
     * @param id id of the ProductCategory to be removed.
     */
    void remove(int id);

    /**
     * Removes all ProductCategories from the storage.
     */
    void removeAll();

    /**
     * Gives back a List of all the existing ProductCategories in the storage.
     * @return List of ProductCategories.
     */
    List<ProductCategory> getAll();

}
