package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.jdbcImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.jdbcImplementation.ProductDaoJDBC;
import com.codecool.shop.dao.jdbcImplementation.SupplierDaoJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * ProductController class is responsible for rendering the index page and showing all the
 * Products stored in the storage.
 */
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private static SupplierDao productSupplierDataStore = SupplierDaoJDBC.getInstance();
    private static ProductDao productDataStore = ProductDaoJDBC.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();

    /**
     * This method renders the index page with all information about the products needed by the
     * front-end.
     * @param req Request object is needed for the session.
     * @param res Response object is needed by Spark.
     * @return Spark ModelAndView
     */
    public static ModelAndView renderProducts(Request req, Response res) {
        logger.info("Successful GET request on the url: '/' or '/index'");
        req.session(true);

        Map indexRenderParams = paramFiller(req);
        indexRenderParams.put("products", productDataStore.getAll());
        return new ModelAndView(indexRenderParams, "product/index");
    }

    /**
     * This method renders the index page with the information about all products filtered by
     * ProductCategory.
     * @param req Request object is needed by paramFiller method.
     * @param res Response object is needed by Spark.
     * @param categoryID This is needed to find the given ProductCategory.
     * @return Spark ModelAndView
     */
    public static ModelAndView renderProductsbyCategory(Request req, Response res, int categoryID) {
        Map categoryRenderParams = paramFiller(req);
        categoryRenderParams.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(categoryRenderParams, "product/index");
    }

    /**
     * This method renders the index page with the information about all products filtered by
     * Supplier.
     * @param req Request objct is needed by paramFiller method.
     * @param res Response is needed by Spark.
     * @param supplierID This is needed to find the given Supplier.
     * @return Spark ModelAndView
     */
    public static ModelAndView renderProductsbySupplier(Request req, Response res, int supplierID) {
        Map supRenderParams = paramFiller(req);
        supRenderParams.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(supRenderParams, "product/index");
    }

    /**
     * This method is responsible for filling "params" HashMap with all the data the frontend page
     * will need upon rendering.
     * @param req Request object is needed to handle session.
     * @return a HashMap filled with parameters needed by the rendering methods.
     */
    public static Map paramFiller(Request req) {
        Map params = new HashMap<>();
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        return params;
    }
}
