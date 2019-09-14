package org.glsid.lightecomv1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> orderItems=new ArrayList<>();
    @ManyToOne
    private Client client;
    private double totalAmount;
    @OneToOne
    private Payement payment;


    public void addOrderItem(OrderItem orderItem) {

        this.orderItems.add(orderItem);
    }

    public String displayOrderItems(){
        String str="";
        for(OrderItem p:this.orderItems){
            str+="id: "+p.getId()+"  name: "+p.getProduct().getName()+" | ";
        }
        return str;
    }
}