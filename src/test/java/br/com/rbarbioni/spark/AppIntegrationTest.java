package br.com.rbarbioni.spark;

import br.com.rbarbioni.spark.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.*;
import org.junit.runners.MethodSorters;
import spark.Spark;

import java.math.BigDecimal;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppIntegrationTest {

    final Gson gson = new Gson();

    private static final String URL = "http://localhost:4567/product";

    private static Product product;

    @BeforeClass
    public static void beforeClass() {
        Application.main(null);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void test1ShouldSuccessPost() throws UnirestException {

        final HttpResponse<JsonNode> response = Unirest.post(URL)
                .body(gson.toJson(new Product("playstation", BigDecimal.valueOf(1200))))
                .asObject(JsonNode.class);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNotNull(response.getBody());
        product = gson.fromJson(response.getBody().toString(), Product.class);
        Assert.assertTrue(product.getId() > 0);
    }

    @Test
    public void test2ShouldSuccessFindById() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(URL + "/" + product.getId()).asObject(JsonNode.class);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(product.getId(),  gson.fromJson(response.getBody().toString(), Product.class).getId());
    }

    @Test
    public void test3ShouldError404FindById() throws UnirestException {
        HttpResponse<String> response = Unirest.get(URL + "/" + Long.MAX_VALUE).asString();
        Assert.assertEquals(404, response.getStatus());
    }

    @Test
    public void test4ShouldSuccessFindAll() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(URL).asObject(JsonNode.class);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNotNull(response.getBody());
        List<Product> products = gson.fromJson(response.getBody().toString(), new TypeToken<List<Product>>(){}.getType());
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() > 0);
    }

    @Test
    public void test5ShouldSuccessUpdate() throws UnirestException {
        int newPrice = 1500;
        this.product.setPrice(BigDecimal.valueOf(newPrice));
        HttpResponse<JsonNode> response = Unirest.put(URL + "/" + product.getId()).body(gson.toJson(product)).asObject(JsonNode.class);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertNotNull(response.getBody());
        Product product = gson.fromJson(response.getBody().toString(), Product.class);
        Assert.assertTrue(product.getId() > 0);
        Assert.assertEquals(newPrice, product.getPrice().longValue());
    }

    @Test
    public void test6ShouldError404Update() throws UnirestException {
        HttpResponse<String> response =  Unirest.put(URL + "/" + Long.MAX_VALUE).body(gson.toJson(product)).asString();
        Assert.assertEquals(404, response.getStatus());
    }
    @Test
    public void test7ShouldSucessDelete() throws UnirestException {
        HttpResponse<String> response = Unirest.delete(URL + "/" + product.getId()).asString();
        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    public void test8ShouldError404Delete() throws UnirestException {
        HttpResponse<String> response = Unirest.delete(URL + "/" + Long.MAX_VALUE).asString();
        Assert.assertEquals(404, response.getStatus());
    }
}