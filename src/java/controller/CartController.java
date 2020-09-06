/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriesDAO;
import dao.FootersDAO;
import dao.ProductsDAO;
import dao.SlidesDAO;
import entity.Orders;
import entity.ProductExt;
import entity.Products;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LeDung
 */
@Controller
public class CartController {

    private ProductsDAO productsDAO;
    private CategoriesDAO categoriesDAO;
    private FootersDAO footersDAO;
    private SlidesDAO slidesDAO;

    @Autowired
    public void setFootersDAO(FootersDAO footersDAO) {
        this.footersDAO = footersDAO;
    }

    @Autowired
    public void setSlidesDAO(SlidesDAO slidesDAO) {
        this.slidesDAO = slidesDAO;
    }

    @Autowired
    public void setProductsDAO(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Autowired
    public void setCategoriesDAO(CategoriesDAO categoriesDAO) {
        this.categoriesDAO = categoriesDAO;
    }

    @RequestMapping(value = {"/them-vao-gio-hang"})
    public String addcart(Model model, HttpServletRequest request) {
        int productID = Integer.parseInt(request.getParameter("productID"));
        int cateId = Integer.parseInt(request.getParameter("cateId"));
        int back = Integer.parseInt(request.getParameter("back"));
        String findName = request.getParameter("name");
        int page = Integer.parseInt(request.getParameter("page"));
        HttpSession session = request.getSession();
        Map<Integer, Integer> listCarts = (Map<Integer, Integer>) session.getAttribute("listCart");
        if (listCarts == null) {
            listCarts = new HashMap<>();
            listCarts.put(productID, 1);
        } else {
            boolean existed = false;
            for (Map.Entry<Integer, Integer> pex : listCarts.entrySet()) {
                if (pex.getKey().equals(productID)) {
                    existed = true;
                    pex.setValue(pex.getValue() + 1);
                    break;
                }
            }
            if (!existed) {
                listCarts.put(productID, 1);
            }
        }
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listP", productsDAO.getIndexProducts());
        model.addAttribute("listHot", productsDAO.getIndexHotProducts());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listS", slidesDAO.getIndexSlides());
        session.setAttribute("listCart", listCarts);
        if (back == 1) {
            return "redirect:indexClient.htm";
        } else if (back == 2) {
            return "redirect:productClientByCategory.htm?categoryID=" + cateId + "&&page=" + page;
        } else if (back == 3) {
            return "redirect:productClientByDetail.htm?productID=" + productID + "&&categoryID=" + cateId;
        } else if (back == 4) {
            return "redirect:findClient.htm?name=" + findName + "&&page=" + page;
        } else if (back == 5) {
            return "redirect:findClientSortNameDesc.htm?name=" + findName + "&&page=" + page;
        } else if (back == 6) {
            return "redirect:findClientSortNameAsc.htm?name=" + findName + "&&page=" + page;
        } else if (back == 7) {
            return "redirect:findClientSortPriceDesc.htm?name=" + findName + "&&page=" + page;
        } else if (back == 8) {
            return "redirect:findClientSortPriceAsc.htm?name=" + findName + "&&page=" + page;
        } else if (back == 9) {
            return "redirect:productClient.htm?page=" + page;
        } else {
            return "indexClient";
        }
    }

    @RequestMapping(value = "/capnhat")
    public String updatecart(Integer key, Integer quantity, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Map<Integer, Integer> listCarts = (Map<Integer, Integer>) session.getAttribute("listCart");
        listCarts.replace(key, quantity);
        return "redirect:giohang.htm";
    }

    @RequestMapping(value = "/xoa")
    public String deletecart(Model model, HttpServletRequest request) {
        int productKey = Integer.parseInt(request.getParameter("key"));

        HttpSession session = request.getSession();
        Map<Integer, Integer> listCarts = (Map<Integer, Integer>) session.getAttribute("listCart");
        listCarts.remove(productKey);
        return "redirect:giohang.htm";
    }

    @RequestMapping(value = {"/giohang"})
    public String viewcart(Model model, HttpServletRequest request
    ) {
        model.addAttribute("o", new Orders());
        HttpSession session = request.getSession();
        Map<Integer, Integer> listCarts = (Map<Integer, Integer>) session.getAttribute("listCart");
        session.setAttribute("totalKindOfProduct", listCarts == null ? 0 : listCarts.size());
        if (listCarts == null) {
            model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
            model.addAttribute("listC", categoriesDAO.getIndexCategorys());
            model.addAttribute("listF", footersDAO.getIndexFooters());
            return "viewCart";
        } else {
            model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
            model.addAttribute("listC", categoriesDAO.getIndexCategorys());
            model.addAttribute("listF", footersDAO.getIndexFooters());
            List<ProductExt> listBuy = new ArrayList<>();
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
                //objP.setTotalPrice(p.getPrice() * objP.getNumberOfProduct()-(p.getPrice() * objP.getNumberOfProduct() - p.getDiscount() * objP.getNumberOfProduct()));
                listBuy.add(objP);
                totalPaid = totalPaid + objP.getTotalPrice();
            }
            request.setAttribute("totalPaid", totalPaid);
            model.addAttribute("listCarts", listBuy);
            return "viewCart";
        }
    }
}
