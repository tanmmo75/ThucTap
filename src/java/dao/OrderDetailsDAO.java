/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.OrderDetails;
import java.util.List;


public interface OrderDetailsDAO {
    public List<OrderDetails> getAllOrderDetails();
    public OrderDetails getOrderDetailById(Integer id);
    public Boolean insertOrderDetail(OrderDetails od);
    public Boolean updateOrderDetail(OrderDetails od);
    public Boolean deleteOrderDetail(Integer id);
}
