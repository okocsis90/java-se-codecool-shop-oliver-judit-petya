package com.codecool.shop.controller;

import com.codecool.shop.dao.jdbcImplementation.ProductDaoJDBC;
import com.codecool.shop.dao.memImplementation.OrderDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

/**
 * OrderController is responsible for managing orders. In this app, an order contains all the
 * items which were added to the cart.
 * It stores all data in the current order via the OrderDaoMem implementation and also in the current session.
 */
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static OrderDaoMem orderList = OrderDaoMem.getInstance();

    /**
     * This method updates the current sessions orderQuantity and orderPrice, which derive from
     * the orders such fields.
     * @param req the Request parameter is needed to handle the session.
     * @param currentOrder the current order contains all necessary information.
     */
    private static void updateSession(Request req, Order currentOrder) {
        req.session().attribute("orderQuantity", currentOrder.getOrderQuantity());
        req.session().attribute("orderPrice", currentOrder.getOrderPrice());
        logger.info("Current order quantity: {} order price: {} in session. ", currentOrder.getOrderQuantity(), currentOrder.getOrderPrice());
    }

    /**
     * This method creates a LineItem based on the request from the webpage. It searches the product
     * and gets the quantity needed, then instantiates and gives back a LineItem based on the information.
     * @param req conatins information about the product and the quantity
     * @return LineItem
     */
    private static LineItem returnLineItemFromReq(Request req) {
        String productIdStr = req.queryParams("prodId");
        String productQuantityStr = req.queryParams("quantity");
        int productQuantityInt = Integer.parseInt(productQuantityStr);
        int productIdInt = Integer.parseInt(productIdStr);
        return new LineItem(ProductDaoJDBC.getInstance().find(productIdInt), productQuantityInt);
    }

    /**
     * This method will return an Order instance. If there is no orderId existing in the session,
     * this method instantiates an Order and stores its id in the session.
     * If there is already an orderId stored in the session, it will find the Order in the List of Orders
     * by its id and return that Order.
     * @param req is needed to handle the session.
     * @return Order
     */
    private static Order findCurrentOrder(Request req) {
        Order currentOrder = new Order();
        if (!req.session().attributes().contains("orderId")) {
            orderList.add(currentOrder);
            req.session().attribute("orderId", currentOrder.getId());
        } else {
            int orderId = req.session().attribute("orderId");
            currentOrder = orderList.find(orderId);
        }
        return currentOrder;
    }

    /**
     * This method is responsible for adding all the logic in this class together. It instantiates
     * a LineItem based on the request, finds the current Order, adds the LineItem to the Order,
     * Updates the session with all necessary Data. Then it also creates a json object from the quantity
     * of all objects existing in the current order, which will be used by a JavaScript file, updating
     * the number of all items in the cart via AJAX.
     * @param req Request object is needed to handle session.
     * @param res Response object is needed by Spark.
     * @return JSON Object for the AJAX
     */
    public static JSONObject addToCart(Request req, Response res) {
        logger.info("Adding to order initialized");
        LineItem selectedItem = returnLineItemFromReq(req);
        Order currentOrder = findCurrentOrder(req);
        currentOrder.addLineItem(selectedItem);
        logger.info("Added product:{} quantity:{} to order no. {}", selectedItem.getProduct().getName(), selectedItem.getQuantity(), currentOrder.getId());
        updateSession(req, currentOrder);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("numOfLineItems", currentOrder.getOrderQuantity());
        res.type("application/json");
        return jsonObj;
    }
}
