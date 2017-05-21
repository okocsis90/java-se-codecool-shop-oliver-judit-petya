package com.codecool.shop.dao.memImplementation;


import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private static ProductCategoryDaoMem instance = null;
    private List<ProductCategory> DATA = new ArrayList<>();

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(DATA.size() + 1);
        DATA.add(category);
        logger.info("ProductCategory: {} added to our mem. ", category.getName());
    }

    @Override
    public ProductCategory find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        if (DATA.size() > 0 && DATA.contains(find(id))) {
            logger.info("ProductCategory '{}' will be removed from mem.", find(id).getName());
        }
        DATA.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return DATA;
    }

    public void removeAll() {
        DATA.clear();
        logger.info("All productCategory deleted from mem.");
    }

}
