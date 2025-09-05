package com.clt.Order_Service.Controller;

import com.clt.Order_Service.DTO.OrderResponseDTO;
import com.clt.Order_Service.DTO.ProductDTO;
import com.clt.Order_Service.Entity.Order;
import com.clt.Order_Service.Repository.OrderRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/placeOrder")
    public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(@RequestBody Order order){
        return webClientBuilder.build().get()
                .uri("http://localhost:8081/products/" +order.getProductId()).retrieve()
                .bodyToMono(ProductDTO.class).map(productDTO -> {
                    OrderResponseDTO responseDTO = new OrderResponseDTO();
                    responseDTO.setOrderId(order.getId());
                    responseDTO.setProductId(order.getProductId());
                    responseDTO.setQuantity(order.getQuantity());

                    responseDTO.setProductName(productDTO.getName());
                    responseDTO.setProductPrice(productDTO.getPrice());
                    responseDTO.setTotalPrice(order.getQuantity() * productDTO.getPrice());

                    orderRepository.save(order);
                    responseDTO.setOrderId(order.getId());
                    return ResponseEntity.ok(responseDTO) ;
                });
    }
@GetMapping
    public List<Order> getAllOrder(){
     return orderRepository.findAll();
}

}
