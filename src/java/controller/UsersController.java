/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserGroupsDAO;
import dao.UsersDAO;
import entity.UserGroups;
import entity.Users;
import entity.UsersInsert;
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
public class UsersController {

    private UsersDAO usersDAO;
    private UserGroupsDAO userGroupsDAO;

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Autowired
    public void setUserGroupsDAO(UserGroupsDAO userGroupsDAO) {
        this.userGroupsDAO = userGroupsDAO;
    }

    @RequestMapping(value = "/listU")
    public String listUsers(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = usersDAO.Count();
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listUser", usersDAO.getAllUsers(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listUser", usersDAO.getAllUsers(currentPage, recordsPerPage));
        }
        return "listUser";
    }

    @RequestMapping(value = "/initfindU")
    public String initfindUser(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            return "redirect:/findU.htm?name=" + name + "&&page=1";
        } //Đặt điều kiện khi ô find bỏ trống
        else {
            return "redirect:/listU.htm?name=" + "" + "&&page=1";
        }
    }

    @RequestMapping(value = "/findU")
    public String findUser(@RequestParam("name") String name, @RequestParam("page") int currentPage, Model model) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = usersDAO.CountFind(name);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentName", name);
        model.addAttribute("listUser", usersDAO.getUsersFindByName(name, currentPage, recordsPerPage));
        return "listUser";
    }

    @RequestMapping(value = "/detailUser")
    public String detail(@RequestParam("userID") Integer userID, Model model) {
        model.addAttribute("e", usersDAO.getUserById(userID));
        return "detailUser";
    }

    @RequestMapping(value = "/initAddUser")
    public String initAdd(Model model) {
        List<UserGroups> listUserGroup = userGroupsDAO.getAllUserGroupsDropDownList();
        model.addAttribute("listUserGroup", listUserGroup);
        model.addAttribute("u", new Users());
        return "addUser";
    }

    @RequestMapping(value = "/addUser")
    public String insertUser(@Valid @ModelAttribute("u") UsersInsert u, BindingResult result, RedirectAttributes attributes, Model model) {
        List<UserGroups> listUserGroup = userGroupsDAO.getAllUserGroupsDropDownList();
        model.addAttribute("listUserGroup", listUserGroup);
        if (result.hasErrors()) {
            return "addUser";
        }
        Users c = new Users();
        c.setUsername(u.getUsername());
        c.setPassword(u.getPassword());
        c.setName(u.getName());
        c.setAddress(u.getAddress());
        c.setEmail(u.getEmail());
        c.setPhone(u.getPhone());
        c.setCreateDate(u.getCreateDate());
        UserGroups u1 = new UserGroups(u.getUserGroupID());
        c.setUserGroupID(u1);
        c.setStatus(u.getStatus());
        Boolean blInsert = usersDAO.insertUser(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listU.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addUser";
        }
    }

    @RequestMapping(value = "/initUpdateUser")
    public String initUpdate(@RequestParam("userID") Integer userID,@RequestParam("profile") Integer profile, Model model) {
        List<UserGroups> listUserGroup = userGroupsDAO.getAllUserGroupsDropDownList();
        model.addAttribute("listUserGroup", listUserGroup);
        Users objA = usersDAO.getUserById(userID);
        model.addAttribute("profilemode",profile);
        model.addAttribute("u", objA);
        return "updateUser";
    }

    @RequestMapping(value = "/updateUser")
    public String updateUser(@Valid @ModelAttribute("u") UsersInsert u, BindingResult result, RedirectAttributes attributes, Model model) {
        List<UserGroups> listUserGroup = userGroupsDAO.getAllUserGroupsDropDownList();
        model.addAttribute("listUserGroup", listUserGroup);
        if (result.hasErrors()) {
            return "updateUser";
        }
        Users c = new Users();
        c.setUserID(u.getUserID());
        c.setUsername(u.getUsername());
        c.setPassword(u.getPassword());
        c.setName(u.getName());
        c.setAddress(u.getAddress());
        c.setEmail(u.getEmail());
        c.setPhone(u.getPhone());
        c.setCreateDate(u.getCreateDate());
        UserGroups u1 = new UserGroups(u.getUserGroupID());
        c.setUserGroupID(u1);
        c.setStatus(u.getStatus());
        Boolean blUpdate = usersDAO.updateUser(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listU.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateUser";
        }
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteEmp(@RequestParam("userID") Integer userID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = usersDAO.deleteUser(userID);
        if (blDelete) {
            model.addAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        String name = request.getParameter("find");
        String page = request.getParameter("page");
        if (name != null && !name.isEmpty()) {
            return "redirect:/findU.htm?name=" + name + "&&page=" + page;
        } else {
            return "redirect:/listU.htm?page=" + page;
        }
    }
}
