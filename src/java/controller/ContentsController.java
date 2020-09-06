/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContentsDAO;
import dao.UsersDAO;
import entity.Contents;
import entity.ContentsInsert;
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
public class ContentsController {

    private ContentsDAO contentsDAO;
    private UsersDAO usersDAO;

    @Autowired
    public void setContentsDAO(ContentsDAO contentsDAO) {
        this.contentsDAO = contentsDAO;
    }

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @RequestMapping(value = "/listCo")
    public String listContents(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = contentsDAO.Count();
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("count", count);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listContent", contentsDAO.getAllContents(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listContent", contentsDAO.getAllContents(currentPage, recordsPerPage));
        }
        return "listContent";
    }

    @RequestMapping(value = "/initfindCo")
    public String initfindContent(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            return "redirect:/findCo.htm?name=" + name + "&&page=1";
        } //Đặt điều kiện khi ô find bỏ trống
        else {
            return "redirect:/listCo.htm?name=" + "" + "&&page=1";
        }
    }

    @RequestMapping(value = "/findCo")
    public String findContent(@RequestParam("name") String name, @RequestParam("page") int currentPage, Model model) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = contentsDAO.CountFind(name); 
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage); 
        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("count", count);


        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentName", name);
        model.addAttribute("listContent", contentsDAO.getContentsFindByName(name, currentPage, recordsPerPage));
        return "listContent";
    }

    @RequestMapping(value = "/detailContent")
    public String detail(@RequestParam("contentID") Integer contentID, Model model) {
        model.addAttribute("e", contentsDAO.getContentById(contentID));
        return "detailContent";
    }

    @RequestMapping(value = "/initAddContent")
    public String initAdd(Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        model.addAttribute("co", new Contents());
        return "addContent";
    }

    @RequestMapping(value = "/addContent")
    public String insertContent(@Valid @ModelAttribute("co") ContentsInsert co, BindingResult result, RedirectAttributes attributes, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        if (result.hasErrors()) {
            return "addContent";
        }
        Contents c = new Contents();
        c.setTitle(co.getTitle());
        c.setImage(co.getImage().substring(1));
        c.setDescription(co.getDescription());
        c.setDetail(co.getDetail());
        c.setContenSource(co.getContenSource());
        c.setCreateDate(co.getCreateDate());
        Users u = new Users(co.getUserID());
        c.setUserID(u);
        c.setStatus(co.getStatus());
        Boolean blInsert = contentsDAO.insertContent(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listCo.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addContent";
        }
    }

    @RequestMapping(value = "/initUpdateContent")
    public String initUpdate(@RequestParam("contentID") Integer contentID, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        Contents objA = contentsDAO.getContentById(contentID);
        model.addAttribute("co", objA);
        return "updateContent";
    }

    @RequestMapping(value = "/updateContent")
    public String updateContent(@Valid @ModelAttribute("co") ContentsInsert co, BindingResult result, RedirectAttributes attributes, Model model) {
        List<Users> listUser = usersDAO.getAllUsersDropDownList();
        model.addAttribute("listUser", listUser);
        if (result.hasErrors()) {
            return "updateContent";
        }
        Contents c = new Contents();
        c.setContentID(co.getContentID());
        c.setTitle(co.getTitle());
        c.setImage(co.getImage().substring(1));
        c.setDescription(co.getDescription());
        c.setDetail(co.getDetail());
        c.setContenSource(co.getContenSource());
        c.setCreateDate(co.getCreateDate());
        Users u = new Users(co.getUserID());
        c.setUserID(u);
        c.setStatus(co.getStatus());
        Boolean blUpdate = contentsDAO.updateContent(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listCo.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateContent";
        }
    }

    @RequestMapping(value = "/deleteContent")
    public String deleteEmp(@RequestParam("contentID") Integer contentID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = contentsDAO.deleteContent(contentID);
        if (blDelete) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            attributes.addFlashAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        String name = request.getParameter("find");
        String page = request.getParameter("page");
        if (name != null && !name.isEmpty()) {
            return "redirect:/findCo.htm?name=" + name + "&&page=" + page;
        } else {
            return "redirect:/listCo.htm?page=" + page;
        }
    }
}
