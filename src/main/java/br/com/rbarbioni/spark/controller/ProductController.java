package br.com.rbarbioni.spark.controller;

import br.com.rbarbioni.spark.service.ProductService;
import com.google.gson.Gson;

import static spark.Spark.*;

public class ProductController {

    private static final String PATH = "/product";

    public ProductController() {
        ProductService productService = new ProductService();
        Gson gson = new Gson();
        get(PATH, ((request, response) -> productService.findAll(request, response)), gson::toJson);
        get(PATH + "/:id", ((request, response) -> productService.findById(request, response)), gson::toJson);
        post(PATH, ((request, response) -> productService.create(request, response)), gson::toJson);
        put(PATH + "/:id", ((request, response) -> productService.update(request, response)), gson::toJson);
        delete(PATH + "/:id", ((request, response) -> productService.delete(request, response)));

        after((req, res) -> res.type("application/json"));
    }

}