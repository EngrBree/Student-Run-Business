package com.bree.inventorymanage.Service;

import com.bree.inventorymanage.Entities.Product;
import com.bree.inventorymanage.Repository.inventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class inventoryService {



    private  final inventoryRepository repo;

    public inventoryService(inventoryRepository repo) {
        this.repo = repo;

    }
    public Mono<Product> addNewInventory(Product product){
       return repo.save(product);
    }

    // if product is available
   public Mono<Boolean> isProductAvailable(Integer productid){
        return repo.findById(productid)
                .map(product -> product.getQuantity()>0)
                .defaultIfEmpty(false);
   }

   // update the inventory
    public Mono<Void> updateInventory(Integer productid,Product updatedProduct){
        return repo.findById(Integer.valueOf(productid))
                .flatMap(existingProduct -> {
                    existingProduct.setQuantity(updatedProduct.getQuantity());
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setOrigin(updatedProduct.getOrigin());
                    existingProduct.setStoreName(updatedProduct.getStoreName());

                    return repo.save(updatedProduct);
                }).then();
    }


}
