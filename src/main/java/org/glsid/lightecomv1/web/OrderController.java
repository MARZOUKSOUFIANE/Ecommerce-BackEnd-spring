package org.glsid.lightecomv1.web;

import org.glsid.lightecomv1.dao.*;
import org.glsid.lightecomv1.entities.Client;
import org.glsid.lightecomv1.entities.Order;
import org.glsid.lightecomv1.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class OrderController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody OrderForm orderForm){
        Client client=new Client();
        client.setName(orderForm.getClient().getName());
        client.setAddress(orderForm.getClient().getAddress());
        client.setEmail(orderForm.getClient().getEmail());
        client.setPhoneNumber(orderForm.getClient().getPhoneNumber());
        client.setUsername(orderForm.getClient().getUsername());
        clientRepository.save(client);

        Order order=new Order();
        order.setClient(client);
        order.setDate(new Date());
        orderRepository.save(order);
        for(OrderProduct p:orderForm.getProducts()){
            OrderItem orderItem=new OrderItem();
            orderItem.setQuantity(p.getQuantity());
            orderItem.setProduct(p.getProduct());
            orderItem.setPrice(p.getProduct().getCurrentPrice());
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
            order.setTotalAmount(order.getTotalAmount()+(p.getQuantity()*p.getProduct().getCurrentPrice()));
            order.addOrderItem(orderItem);
        }
        return orderRepository.save(order);
    }
}
