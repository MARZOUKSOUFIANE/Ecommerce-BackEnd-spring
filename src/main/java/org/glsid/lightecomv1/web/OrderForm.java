package org.glsid.lightecomv1.web;

import lombok.Data;
import org.glsid.lightecomv1.entities.Client;
import org.glsid.lightecomv1.entities.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class OrderForm implements Serializable {
    private Client client=new Client();
    private List<OrderProduct> products=new ArrayList<>();
}
@Data
class OrderProduct{
    private Product product;
    private int quantity;

}