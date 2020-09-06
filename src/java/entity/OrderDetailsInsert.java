/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;


public class OrderDetailsInsert implements Serializable {

    private Integer productID;
    private Integer orderID;
    private Integer quantity;
    private Double price;
    private Double discount;

    public OrderDetailsInsert() {
    }

    public OrderDetailsInsert(Integer productID, Integer orderID, Integer quantity, Double price, Double discount) {
        this.productID = productID;
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
