package br.com.rbarbioni.spark.service;

import br.com.rbarbioni.spark.model.Product;
import br.com.rbarbioni.spark.repository.ProductRepository;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public List<Product> findAll (Request req, Response res){
        return this.productRepository.findAll();
    }

    public Product findById(Request request, Response response) {
        Product product = this.productRepository.findById(Long.valueOf(request.params(":id")));
        if(product == null){
            response.status(404);
            return null;
        }
        return product;
    }

    public Product create(Request request, Response response) {
        return this.productRepository.create(new Gson().fromJson(request.body(), Product.class));
    }

    public Product update(Request request, Response response) {
        Product product = this.productRepository.update(Long.valueOf(request.params(":id")), new Gson().fromJson(request.body(), Product.class));
        if(product == null){
            response.status(404);
            return null;
        }
        return product;
    }

    public Boolean delete(Request request, Response response) {
        Product product = this.productRepository.delete(Long.valueOf(request.params(":id")));
        if(product == null){
            response.status(404);
            return null;
        }
        response.status(204);
        return Boolean.TRUE;
    }
}


