/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriesDAO;
import dao.UsersDAO;
import entity.Categories;
import entity.CategoriesInsert;
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
public class CategoriesController {

    private CategoriesDAO categoriesDAO;
    private UsersDAO usersDAO;

    @Autowired
    public void setCategoriesDAO(CategoriesDAO categoriesDAO) {
        this.categoriesDAO = categoriesDAO;
    }

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @RequestMapping(value = "/listCate")
    public String listCategorys(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = categoriesDAO.Count();
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listCategory", categoriesDAO.getAllCategorys(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listCategory", categoriesDAO.getAllCategorys(currentPage, recordsPerPage));
        }
        return "listCategory";
    }

    @RequestMapping(value = "/initfindCate")
    public String initfindCategory(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            return "redirect:/findCate.htm?name=" + name + "&&page=1";
        } //Đặt điều kiện khi ô find bỏ trống
        else {
            return "redirect:/listCate.htm?name=" + "" + "&&page=1";
        }
    }

    @RequestMapping(value = "/findCate")
    public String findCategory(@RequestParam("name") String name, @RequestParam("page") int currentPage, Model model) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = categoriesDAO.CountFind(name);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentName", name);
        model.addAttribute("listCategory", categoriesDAO.getCategorysFindByName(name, currentPage, recordsPerPage));
        return "listCategory";
    }

    @RequestMapping(value = "/detailCategory")
    public String detail(@RequestParam("categoryID") Integer categoryID, Model model) {
        model.addAttribute("e", categoriesDAO.getCategoryById(categoryID));
        return "detailCategory";
    }

    @RequestMapping(value = "/initAddCategory")
    public String initAdd(Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("cate", new Categories());
        model.addAttribute("listUser", listUser);
        return "addCategory";
    }

    @RequestMapping(value = "/addCategory")
    public String insertCategory(@Valid @ModelAttribute("cate") CategoriesInsert cate, BindingResult result, RedirectAttributes attributes, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        if (result.hasErrors()) {
            return "addCategory";
        }
        Categories c = new Categories();
        c.setName(cate.getName());
        c.setCreateDate(cate.getCreateDate());
        c.setDisplayOrder(cate.getDisplayOrder());
        c.setParenId(cate.getParenId());
        c.setStatus(cate.getStatus());
        Users u = new Users(cate.getUserID());
        c.setUserID(u);
        Boolean blInsert = categoriesDAO.insertCategory(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listCate.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addCategory";
        }
    }

    @RequestMapping(value = "/initUpdateCategory")
    public String initUpdate(@RequestParam("categoryID") Integer categoryID, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        Categories objCate = categoriesDAO.getCategoryById(categoryID);
        model.addAttribute("cate", objCate);
        return "updateCategory";
    }

    @RequestMapping(value = "/updateCategory")
    public String updateCategory(@Valid @ModelAttribute("cate") CategoriesInsert cate, BindingResult result, RedirectAttributes attributes, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        if (result.hasErrors()) {
            return "updateCategory";
        }
        Categories c = new Categories();
        c.setCategoryID(cate.getCategoryID());
        c.setName(cate.getName());
        c.setCreateDate(cate.getCreateDate());
        c.setDisplayOrder(cate.getDisplayOrder());
        c.setParenId(cate.getParenId());
        c.setStatus(cate.getStatus());
        Users u = new Users(cate.getUserID());
        c.setUserID(u);
        Boolean blUpdate = categoriesDAO.updateCategory(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listCate.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateCategory";
        }
    }

    @RequestMapping(value = "/deleteCategory")
    public String deleteEmp(@RequestParam("categoryID") Integer categoryID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = categoriesDAO.deleteCategory(categoryID);
        if (blDelete) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            attributes.addFlashAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        String name = request.getParameter("find");
        String page = request.getParameter("page");
        if (name != null && !name.isEmpty()) {
            return "redirect:/findCate.htm?name=" + name + "&&page=" + page;
        } else {
            return "redirect:/listCate.htm?page=" + page;
        }
    }
}
