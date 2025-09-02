package com.clt.Product_Service.Controller;

import com.clt.Product_Service.Entity.Product;
import com.clt.Product_Service.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
private ProductRepository productRepository;

    @PostMapping
    public Product AddProduct (@RequestBody  Product product){
        return productRepository.save(product);
    }
    @GetMapping
    public List<Product> GetProduct (){
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> GetProductById (@PathVariable Long id){
      Product product = productRepository.findById(id)
              .orElseThrow(()->new RuntimeException("product not found with id "+id));
      return ResponseEntity.ok(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> DeleteProductById (@PathVariable Long id) {
      if(productRepository.existsById(id)){
          productRepository.deleteById(id);
          return ResponseEntity.noContent().build();
      }else {
          return ResponseEntity.notFound().build();
      }

    }



}
