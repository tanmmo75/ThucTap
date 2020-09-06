/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDetailsDAO;
import entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dell
 */
@Controller
public class OrderDetailsController {

    private OrderDetailsDAO orderDetailsDAO;

    @Autowired
    public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
        this.orderDetailsDAO = orderDetailsDAO;
    }

    @RequestMapping(value = "/listOD")
    public String listOrderDetails(Model model) {
        model.addAttribute("listOrderDetail", orderDetailsDAO.getAllOrderDetails());
        return "listOrderDetail";
    }

    @RequestMapping(value = "/detailOrderDetail")
    public String detail(@RequestParam("aboutID") Integer aboutID, Model model) {
        model.addAttribute("e", orderDetailsDAO.getOrderDetailById(aboutID));
        return "detailOrderDetail";
    }

    @RequestMapping(value = "/initAddOrderDetail")
    public String initAdd(Model model) {
        model.addAttribute("e", new OrderDetails());
        return "addOrderDetail";
    }

    @RequestMapping(value = "/addOrderDetail")
    public String insertOrderDetail(@ModelAttribute("e") OrderDetails e, BindingResult result, Model model) {
        Boolean blInsert = orderDetailsDAO.insertOrderDetail(e);
        if (blInsert) {
            return "redirect:/listOD.htm";
        } else {
            model.addAttribute("status", "Add employee failed!");
            return "addOrderDetail";
        }
    }

    @RequestMapping(value = "/initUpdateOrderDetail")
    public String initUpdate(@RequestParam("aboutID") Integer aboutID, Model model) {
        OrderDetails objA = orderDetailsDAO.getOrderDetailById(aboutID);
        model.addAttribute("e", objA);
        return "updateOrderDetail";
    }

    @RequestMapping(value = "/updateOrderDetail")
    public String updateOrderDetail(@ModelAttribute("e") OrderDetails e, BindingResult result, Model model) {
        Boolean blUpdate = orderDetailsDAO.updateOrderDetail(e);
        if (blUpdate) {
            return "redirect:/listOD.htm";
        } else {
            model.addAttribute("status", "Update employee failed!");
            return "updateOrderDetail";
        }
    }

    @RequestMapping(value = "/deleteOrderDetail")
    public String deleteEmp(@RequestParam("aboutID") Integer aboutID, Model model) {
        Boolean blDelete = orderDetailsDAO.deleteOrderDetail(aboutID);
        if (blDelete) {
            model.addAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        model.addAttribute("listOrderDetail", orderDetailsDAO.getAllOrderDetails());
        return "listOrderDetail";
    }
}
