package br.com.rbarbioni.spark.repository;

import br.com.rbarbioni.spark.model.Product;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProductRepository {

    private static final Map<Long, Product> PRODUCTS = Collections.synchronizedMap(new HashMap<Long, Product>());

    private final AtomicLong atomicId = new AtomicLong(1);

    public List<Product> findAll(){
        return PRODUCTS.entrySet()
                .stream()
                .map(p -> p.getValue())
                .collect(Collectors.toList());
    }

    public Product findById(final Long id){
        return PRODUCTS.entrySet()
                .stream()
                .map(p -> p.getValue())
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product create (Product product){
        long id = atomicId.getAndIncrement();
        product.setId(id);
        PRODUCTS.put(id, product);
        return product;
    }

    public Product update (Long id, Product product){
        Product exists = findById(id);
        if(exists == null){
            return null;
        }
        PRODUCTS.put(id, product);
        return product;
    }

    public Product delete (Long id){
        return PRODUCTS.remove(id);
    }
}
