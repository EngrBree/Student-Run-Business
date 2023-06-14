package com.bree.inventorymanage.Controller;

import com.bree.inventorymanage.Entities.Product;
import com.bree.inventorymanage.Service.inventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("products")
public class InventoryController {

    //private WebClient.Builder webClientBuilder;
    private  final inventoryService inventoryservice;

    public InventoryController(inventoryService inventoryservice) {
        this.inventoryservice = inventoryservice;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> addNewInventory(@RequestBody Product product) {
        return  inventoryservice.addNewInventory(product);

    }
    @GetMapping("/{productid}/availability")
    public Mono<Boolean> isProductAvailable(@PathVariable Integer productid){
        return inventoryservice.isProductAvailable(productid);
    }
  @PutMapping("/products/{productid}")
    public Mono<Void> updateInventory(@PathVariable Integer productId, @RequestBody Product product){
        return  inventoryservice.updateInventory(productId,product);
  }


}
