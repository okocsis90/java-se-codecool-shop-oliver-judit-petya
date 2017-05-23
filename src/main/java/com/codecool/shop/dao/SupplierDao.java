package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

/**
 * SupplierDao is responsible for storing, searching and removing Suppliers.
 */
public interface SupplierDao {

    /**
     * Adds a specific Supplier to the storage.
     * @param supplier the Supplier object to be stored.
     */
    void add(Supplier supplier);

    /**
     * Finds and gives back a Supplier by id from the storage.
     * @param id the id of the Supplier object needed.
     * @return Supplier
     */
    Supplier find(int id);

    /**
     * Removes a Supplier by id from the storage.
     * @param id the id of the Supplier object to be removed.
     */
    void remove(int id);

    /**
     * Removes all Suppliers from the storage.
     */
    void removeAll();

    /**
     * Gives back all the existing Suppliers from the storage.
     * @return List of Suppliers.
     */
    List<Supplier> getAll();

}
