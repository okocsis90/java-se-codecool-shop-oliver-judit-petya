package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

/**
 * OrderDao is responsible for storing, searching and removing orders.
 */
public interface OrderDao {

    /**
     * Adds a specific order to the storage.
     * @param order The Order object to be stored.
     */
    void add(Order order);

    /**
     * Finds and gives back an Order by id from the storage.
     * @param id The id of the Order needed.
     * @return Order
     */
    Order find(int id);

    /**
     * Removes an Order by id from the storage.
     * @param id the id of the Order to be removed.
     */
    void remove(int id);

    /**
     * Gives back all the Orders stored.
     * @return List of Orders.
     */
    List<Order> getAll();
}
