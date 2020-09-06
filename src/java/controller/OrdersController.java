/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriesDAO;
import dao.FootersDAO;
import dao.OrderDetailsDAO;
import dao.OrdersDAO;
import dao.ProductsDAO;
import entity.OrderDetails;
import entity.OrderDetailsPK;
import entity.Orders;
import entity.OrdersInsert;
import entity.ProductExt;
import entity.Products;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.bind.ParseConversionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Dell
 */
@Controller
public class OrdersController {

    private OrdersDAO ordersDAO;
    private ProductsDAO productsDAO;
    private CategoriesDAO categoriesDAO;
    private FootersDAO footersDAO;
    private OrderDetailsDAO orderDetailsDAO;

    @Autowired
    public void setOrderDetailsDAO(OrderDetailsDAO orderDetailsDAO) {
        this.orderDetailsDAO = orderDetailsDAO;
    }

    @Autowired
    public void setFootersDAO(FootersDAO footersDAO) {
        this.footersDAO = footersDAO;
    }

    @Autowired
    public void setProductsDAO(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Autowired
    public void setCategoriesDAO(CategoriesDAO categoriesDAO) {
        this.categoriesDAO = categoriesDAO;
    }

    @Autowired
    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @RequestMapping(value = "/listO")
    public String listOrders(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = ordersDAO.Count(1);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrder", ordersDAO.getAllOrders(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrder", ordersDAO.getAllOrders(currentPage, recordsPerPage));
        }
        return "listOrder";
    }

    @RequestMapping(value = "/detailOrder")
    public String detail(@RequestParam("orderID") Integer orderID, Model model) {
        model.addAttribute("e", ordersDAO.getOrderById(orderID));
        return "detailOrder";
    }

    @RequestMapping(value = "/initAddOrder")
    public String initAdd(Model model) {
        model.addAttribute("o", new Orders());
        return "addOrder";
    }

    @RequestMapping(value = "/addOrder")
    public String insertOrder(@Valid @ModelAttribute("o") OrdersInsert o, BindingResult result, RedirectAttributes attributes, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<Integer, Integer> listCarts = (Map<Integer, Integer>) session.getAttribute("listCart");
        List<ProductExt> listBuy = new ArrayList<>();
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (result.hasErrors()) {
            double totalPaid = 0;
            for (Map.Entry<Integer, Integer> product : listCarts.entrySet()) {
                ProductExt objP = new ProductExt();
                objP.setProductID(product.getKey());
                Products p = productsDAO.getProductById(objP.getProductID());
                objP.setName(p.getName());
                //objP.setPrice(p.getPrice());
                //objP.setDiscount(p.getDiscount());
                objP.setImage(p.getImage());
                objP.setNumberOfProduct(product.getValue());
                //objP.setTotalPrice(p.getPrice() * objP.getNumberOfProduct() - p.getDiscount() * objP.getNumberOfProduct());
                listBuy.add(objP);
                totalPaid = totalPaid + objP.getTotalPrice();
            }
            request.setAttribute("totalPaid", totalPaid);
            model.addAttribute("listCarts", listBuy);
            return "viewCart";
        }

        Orders c = new Orders();
        c.setCreateDate(o.getCreateDate());
        c.setShipName(o.getShipName());
        c.setShipMobile(o.getShipMobile());
        c.setShipAdress(o.getShipAdress());
        c.setShipEmai(o.getShipEmai());
        c.setStatus(o.getStatus());
        Boolean blInsert = ordersDAO.insertOrder(c);

        if (blInsert) {
            Integer order = ordersDAO.getLastOrders();
            for (Map.Entry<Integer, Integer> product : listCarts.entrySet()) {
                OrderDetails objO = new OrderDetails();
                Products p = productsDAO.getProductById(product.getKey());
                OrderDetailsPK odpk = new OrderDetailsPK(p.getProductID(), order);
                objO.setOrderDetailsPK(odpk);
                objO.setProducts(p);
                Orders od = new Orders(order);
                objO.setOrders(od);
                objO.setQuantity(product.getValue());
                //objO.setPrice(p.getPrice());
                //objO.setDiscount(p.getDiscount());
                boolean blAdd = orderDetailsDAO.insertOrderDetail(objO);

            }

            attributes.addFlashAttribute("notificationsSuccessfully", "Đơn hàng của bạn đã được gửi thành công!!");
            session.invalidate();
            return "redirect:/giohang.htm";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi gửi đơn hàng!");
            return "viewCart";
        }
    }

    @RequestMapping(value = "/initUpdateOrder")
    public String initUpdate(@RequestParam("orderID") Integer orderID, Model model) {
        model.addAttribute("o", ordersDAO.getOrderById(orderID));
        model.addAttribute("list", ordersDAO.getOrderByListId(orderID));
        model.addAttribute("objOST", ordersDAO.getReportOrderTotalSum(orderID));
//        OrderDetails objA = ordersDAO.getOrderByListId(orderID);
//        model.addAttribute("o", objA);
        return "updateOrder";
    }

    @RequestMapping(value = "/updateOrder")
    public String updateOrder(@Valid @ModelAttribute("o") OrdersInsert o, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "updateOrder";
        }
        Orders c = new Orders();
        c.setOrderID(o.getOrderID());
        c.setCreateDate(o.getCreateDate());
        c.setShipName(o.getShipName());
        c.setShipMobile(o.getShipMobile());
        c.setShipAdress(o.getShipAdress());
        c.setShipEmai(o.getShipEmai());
        c.setStatus(o.getStatus());
        Boolean blUpdate = ordersDAO.updateOrder(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listO.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateOrder";
        }
    }

    @RequestMapping(value = "/deleteOrder")
    public String deleteEmp(@RequestParam("orderID") Integer orderID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = ordersDAO.deleteOrder(orderID);
        if (blDelete) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Đơn hàng đã xóa thành công!");
        } else {
            attributes.addFlashAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        int status = Integer.parseInt(request.getParameter("status"));
        String page = request.getParameter("page");
        if (status == 1) {
            return "redirect:/listOrdersStatus1.htm?page=" + page;
        } else if (status == 2) {
            return "redirect:/listOrdersStatus2.htm?page=" + page;
        } else if (status == 3) {
            return "redirect:/listOrdersStatus3.htm?page=" + page;
        } else {
            return "redirect:/listO.htm?page=" + page;
        }
    }

    @RequestMapping(value = "/listOrdersStatus1")
    public String listOrdersStatus1(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = ordersDAO.Count(2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrdersStatus1", ordersDAO.getAllStatus1(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrdersStatus1", ordersDAO.getAllStatus1(currentPage, recordsPerPage));
        }
        return "listOrdersStatus1";
    }

    @RequestMapping(value = "/listOrdersStatus2")
    public String listOrdersStatus2(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = ordersDAO.Count(3);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrdersStatus2", ordersDAO.getAllStatus2(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrdersStatus2", ordersDAO.getAllStatus2(currentPage, recordsPerPage));
        }
        return "listOrdersStatus2";
    }

    @RequestMapping(value = "/listOrdersStatus3")
    public String listOrdersStatus3(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = ordersDAO.Count(4);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrdersStatus3", ordersDAO.getAllStatus3(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listOrdersStatus3", ordersDAO.getAllStatus3(currentPage, recordsPerPage));
        }
        return "listOrdersStatus3";
    }
}
