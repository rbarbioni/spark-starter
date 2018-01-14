package br.com.rbarbioni.spark;

import br.com.rbarbioni.spark.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        new ProductController();
        exception(Exception.class, (exception, request, response) -> {
            LOG.error("Error", exception);
        });
    }
}
