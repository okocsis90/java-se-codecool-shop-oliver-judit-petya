import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.ProductController;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * The task of the main class is to navigate between the given routes and the controller classes.
 */
public class Main {

    public static void main(String[] args) {


        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
//        populateData();

        /**
         * This route calls the addToCart method of the OrderController. The current Product, from which
         * the Add To Cart button has been clicked will be added to the current Order stored in the session.
         * So that all the Products with the chosen quantities (by default 1) will be stored in the Order.
         */
        post("/add_to_cart", OrderController::addToCart);

        /**
         * This route is responsible for rendering the main Page with all the data of the Products stored
         * in the storage.
         */
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        /**
         * This route ensures that on the index page, the products are filterable by categories.
         */
        get("/category/:id", (Request req, Response res) -> {
            int categoryID = Integer.parseInt(req.params(":id"));
            return new ThymeleafTemplateEngine().render(ProductController.renderProductsbyCategory(req, res, categoryID));
        });
        /**
         * This route ensures that on the index page, the products are filterable by suppliers.
         */
        get("/supplier/:id", (Request req, Response res) -> {
            int supplierID = Integer.parseInt(req.params(":id"));
            return new ThymeleafTemplateEngine().render(ProductController.renderProductsbySupplier(req, res, supplierID));
        });


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

/*    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        ProductCategory phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        productCategoryDataStore.add(phone);
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "Like a tablet but with keyboard");
        productCategoryDataStore.add(notebook);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Super Telephone 3000", 90, "USD", "The best telephone on the planet.", phone, lenovo));

    }*/


}
