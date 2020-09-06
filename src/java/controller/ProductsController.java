/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriesDAO;
import dao.ProducersDAO;
import dao.ProductsDAO;
import dao.UsersDAO;
import entity.Categories;
import entity.Producers;
import entity.Products;
import entity.ProductsInsert;
import entity.Users;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
public class ProductsController {

    private ProductsDAO productsDAO;
    private UsersDAO usersDAO;
    private CategoriesDAO categoriesDAO;
    private ProducersDAO producersDAO;

    @Autowired
    public void setProductsDAO(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Autowired
    public void setCategoriesDAO(CategoriesDAO categoriesDAO) {
        this.categoriesDAO = categoriesDAO;
    }

    @Autowired
    public void setProducersDAO(ProducersDAO producersDAO) {
        this.producersDAO = producersDAO;
    }

    @RequestMapping(value = "/listP")
    public String listProducts(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.Count(1);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProduct", productsDAO.getAllProducts(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProduct", productsDAO.getAllProducts(currentPage, recordsPerPage));
        }
        return "listProduct";
    }

    @RequestMapping(value = "/initfindP")
    public String initfindProduct(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            return "redirect:/findP.htm?name=" + name + "&&page=1";
        }
        //Đặt điều kiện khi ô find bỏ trống
        else {
            return "redirect:/listP.htm?name="+""+"&&page=1";
        }
    }

    @RequestMapping(value = "/findP")
    public String findProduct(@RequestParam("name") String name,@RequestParam("page") int currentPage, Model model) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.CountFind(name,1);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);
        
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentName", name);
        model.addAttribute("listProduct", productsDAO.getProductsFindByName(name, currentPage, recordsPerPage, 1));
        return "listProduct";
    }

    @RequestMapping(value = "/detailProduct")
    public String detail(@RequestParam("productID") Integer productID, Model model) {
        model.addAttribute("e", productsDAO.getProductById(productID));
        return "detailProduct";
    }

    @RequestMapping(value = "/initAddProduct")
    public String initAdd(Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        List<Categories> listCategory = categoriesDAO.getAllCategorysDropDownList();
        model.addAttribute("listCategory", listCategory);
        List<Producers> listProducer = producersDAO.getAllProducersDropDownList();
        model.addAttribute("listProducer", listProducer);
        model.addAttribute("p", new Products());
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct")
    public String insertProduct(@Valid @ModelAttribute("p") ProductsInsert p, BindingResult result, RedirectAttributes attributes, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        List<Categories> listCategory = categoriesDAO.getAllCategorysDropDownList();
        model.addAttribute("listCategory", listCategory);
        List<Producers> listProducer = producersDAO.getAllProducersDropDownList();
        model.addAttribute("listProducer", listProducer);
        if (result.hasErrors()) {
            return "addProduct";
        }
        Products c = new Products();
        c.setName(p.getName());
        c.setCode(p.getCode());
        c.setImage(p.getImage().substring(1));
        c.setDescription(p.getDescription());
        c.setDetail(p.getDetail());
        c.setSpecial(p.getSpecial());
        c.setCreateDate(p.getCreateDate());
        c.setViews(p.getViews());
        Users u = new Users(p.getUserID());
        c.setUserID(u);
        Producers pro = new Producers(p.getProducerID());
        c.setProducerID(pro);
        Categories cate = new Categories(p.getCategoryID());
        c.setCategoryID(cate);
        c.setStatus(p.getStatus());
        Boolean blInsert = productsDAO.insertProduct(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listP.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addProduct";
        }
    }

    @RequestMapping(value = "/initUpdateProduct")
    public String initUpdate(@RequestParam("productID") Integer productID, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        List<Categories> listCategory = categoriesDAO.getAllCategorysDropDownList();
        model.addAttribute("listCategory", listCategory);
        List<Producers> listProducer = producersDAO.getAllProducersDropDownList();
        model.addAttribute("listProducer", listProducer);
        Products objA = productsDAO.getProductById(productID);
        model.addAttribute("p", objA);
        return "updateProduct";
    }

    @RequestMapping(value = "/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("p") ProductsInsert p, BindingResult result, RedirectAttributes attributes, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        List<Categories> listCategory = categoriesDAO.getAllCategorysDropDownList();
        model.addAttribute("listCategory", listCategory);
        List<Producers> listProducer = producersDAO.getAllProducersDropDownList();
        model.addAttribute("listProducer", listProducer);
        if (result.hasErrors()) {
            return "updateProduct";
        }
        Products c = new Products();
        c.setProductID(p.getProductID());
        c.setName(p.getName());
        c.setCode(p.getCode());
        c.setImage(p.getImage().substring(1));
        c.setDescription(p.getDescription());
        c.setDetail(p.getDetail());
        c.setSpecial(p.getSpecial());
        c.setCreateDate(p.getCreateDate());
        c.setViews(p.getViews());
        Users u = new Users(p.getUserID());
        c.setUserID(u);
        Producers pro = new Producers(p.getProducerID());
        c.setProducerID(pro);
        Categories cate = new Categories(p.getCategoryID());
        c.setCategoryID(cate);
        c.setStatus(p.getStatus());
        Boolean blUpdate = productsDAO.updateProduct(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listP.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateProduct";
        }
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteEmp(@RequestParam("productID") Integer productID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = productsDAO.deleteProduct(productID);
        if (blDelete) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            attributes.addFlashAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        String name = request.getParameter("find");
        String page = request.getParameter("page");
        if (name != null && !name.isEmpty()) {
            return "redirect:/findP.htm?name=" + name + "&&page=" + page;
        } else {
            return "redirect:/listP.htm?page=" + page;
        }
    }
}
