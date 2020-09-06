/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AboutsDAO;
import dao.CategoriesDAO;
import dao.ContactsDAO;
import dao.ContentsDAO;
import dao.FootersDAO;
import dao.ProductsDAO;
import dao.SlidesDAO;
import dao.UsersDAO;
import entity.FeedBacks;
import entity.UserGroups;
import entity.Users;
import entity.UsersInsert;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class HomeController {

    private ProductsDAO productsDAO;
    private CategoriesDAO categoriesDAO;
    private FootersDAO footersDAO;
    private SlidesDAO slidesDAO;
    private AboutsDAO aboutsDAO;
    private ContactsDAO contactsDAO;
    private ContentsDAO contentsDAO;
    private UsersDAO usersDAO;

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Autowired
    public void setContentsDAO(ContentsDAO contentsDAO) {
        this.contentsDAO = contentsDAO;
    }

    @Autowired
    public void setContactsDAO(ContactsDAO contactsDAO) {
        this.contactsDAO = contactsDAO;
    }

    @Autowired
    public void setAboutsDAO(AboutsDAO aboutsDAO) {
        this.aboutsDAO = aboutsDAO;
    }

    @Autowired
    public void setSlidesDAO(SlidesDAO slidesDAO) {
        this.slidesDAO = slidesDAO;
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

    @RequestMapping(value = "/index")
    public String indexAdmins() {
        return "indexAdmin";
    }

    @RequestMapping(value = "/indexloginAdmin")
    public String indexloginAdmins(Model model) {
        model.addAttribute("lg", new Users());
        return "loginAdmin";
    }

    @RequestMapping(value = "/login")
    public String loginAdmins(@Valid @ModelAttribute("lg") UsersInsert lg, BindingResult result, RedirectAttributes attributes, HttpServletRequest request, Model model, @RequestParam String username, @RequestParam String password) {
        try {
            if (result.hasErrors()) {
                return "loginAdmin";
            }
            HttpSession session = request.getSession();
            Users login = usersDAO.getUsersLogin(username, password);

            if (username.equals(login.getUsername()) && password.equals(login.getPassword())) {
                session.setAttribute("username", username);
                session.setAttribute("userid", login.getUserID());
                return "redirect:index.htm";
            }
        } catch (Exception ex) {
            request.setAttribute("message", "Tài khoản đăng nhập không đúng hoặc sai mật khẩu");
        }
        return "loginAdmin";
    }

    @RequestMapping(value = "/logoutAdmin")
    public String logoutAdmins(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:indexloginAdmin.htm";
    }

    @RequestMapping(value = "/indexloginClient")
    public String indexloginClients(Model model) {
        model.addAttribute("lg", new Users());
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        return "loginClient";
    }

    @RequestMapping(value = "/loginClient")
    public String loginClients(@Valid @ModelAttribute("lg") UsersInsert lg, BindingResult result, RedirectAttributes attributes, HttpServletRequest request, Model model, @RequestParam String username, @RequestParam String password) {
        try {
            model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
            model.addAttribute("listC", categoriesDAO.getIndexCategorys());
            model.addAttribute("listF", footersDAO.getIndexFooters());
            if (result.hasErrors()) {
                return "loginClient";
            }
            HttpSession session = request.getSession();
            Users login = usersDAO.getUsersLoginClient(username, password);

            if (username.equals(login.getUsername()) && password.equals(login.getPassword())) {
                session.setAttribute("usernameClient", username);
                session.setAttribute("useridClient", login.getUserID());

                return "redirect:indexClient.htm";
            }
        } catch (Exception ex) {
            request.setAttribute("message", "Tài khoản đăng nhập không đúng hoặc sai mật khẩu");
        }
        return "loginClient";
    }

    @RequestMapping(value = "/logoutClient")
    public String logoutClients(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:indexClient.htm";
    }

    @RequestMapping(value = "/indexClient")
    public String indexClient(Model model) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listP", productsDAO.getIndexProducts());
        model.addAttribute("listHot", productsDAO.getIndexHotProducts());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listS", slidesDAO.getIndexSlides());
        
        
            return "indexClient";
    }

    @RequestMapping(value = "/aboutClient")
    public String aboutClient(Model model) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listAbout", aboutsDAO.getAllAbouts(1, 10));
        return "aboutClient";
    }

    @RequestMapping(value = "/contactClient")
    public String contactClient(Model model) {

        model.addAttribute("fb", new FeedBacks());
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listContact", contactsDAO.getAllContacts());
        return "contactClient";
    }

    @RequestMapping(value = "/contentClient")
    public String contentClient(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 9;
        //Tính tổng số trang và làm tròn
        long count = contentsDAO.Count();
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listContent", contentsDAO.getAllContents(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listContent", contentsDAO.getAllContents(currentPage, recordsPerPage));
        }
        return "contentClient";
    }

    @RequestMapping(value = "/contentClientByDetail")
    public String contentClientByDetail(Model model, @RequestParam Integer contentID) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listIndexContentDetail", contentsDAO.getContentById(contentID));
        return "contentClientByDetail";
    }

    @RequestMapping(value = "/productClient")
    public String productClient(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.Count(2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());

        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProduct", productsDAO.getAllIndexProducts(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProduct", productsDAO.getAllIndexProducts(currentPage, recordsPerPage));
        }
        return "productClient";
    }

    @RequestMapping(value = "/productClientSortNameAsc")
    public String productClientSortNameAsc(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.Count(2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortNameAsc", productsDAO.getAllProductsSortNameAsc(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortNameAsc", productsDAO.getAllProductsSortNameAsc(currentPage, recordsPerPage));
        }
        return "productClientSortNameAsc";
    }

    @RequestMapping(value = "/productClientSortNameDesc")
    public String productClientSortNameDesc(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.Count(2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortNameDesc", productsDAO.getAllProductsSortNameDesc(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortNameDesc", productsDAO.getAllProductsSortNameDesc(currentPage, recordsPerPage));
        }
        return "productClientSortNameDesc";
    }

    @RequestMapping(value = "/productClientSortPriceAsc")
    public String productClientSortPriceAsc(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.Count(2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortPriceAsc", productsDAO.getAllProductsSortPriceAsc(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortPriceAsc", productsDAO.getAllProductsSortPriceAsc(currentPage, recordsPerPage));
        }
        return "productClientSortPriceAsc";
    }

    @RequestMapping(value = "/productClientSortPriceDesc")
    public String productClientSortPriceDesc(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.Count(2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortPriceDesc", productsDAO.getAllProductsSortPriceDesc(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProductSortPriceDesc", productsDAO.getAllProductsSortPriceDesc(currentPage, recordsPerPage));
        }
        return "productClientSortPriceDesc";
    }

    @RequestMapping(value = "/productClientByCategory")
    public String productClientByCategory(Model model, @RequestParam Integer categoryID, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.CountbyCategory(categoryID);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("id", categoryID);
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listP", productsDAO.getProductsByCategorys(categoryID, currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listP", productsDAO.getProductsByCategorys(categoryID, currentPage, recordsPerPage));
        }
        return "productClientByCategory";
    }

    @RequestMapping(value = "/productClientByDetail")
    public String productClientByDetail(Model model, @RequestParam Integer categoryID, @RequestParam Integer productID) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listIndexDetail", productsDAO.getProductById(productID));
        model.addAttribute("productRelated", productsDAO.getProductsRelated(categoryID, productID));
        return "productClientByDetail";
    }

    @RequestMapping(value = "/initIndexAddUser")
    public String initAdd(Model model) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("u", new Users());
        return "addIndexUser";
    }
    @RequestMapping(value = "/initIndexUpdateUser")
    public String initUpdate(@RequestParam("userID") Integer userID,Model model) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        
        Users objA = usersDAO.getUserById(userID);
        model.addAttribute("u", objA);
        return "updateIndexUser";
    }

    @RequestMapping(value = "/addIndexUser")
    public String insertUser(@Valid @ModelAttribute("u") UsersInsert u, BindingResult result, RedirectAttributes attributes, Model model) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (result.hasErrors()) {
            return "addIndexUser";
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
            attributes.addFlashAttribute("notificationsSuccessfully", "tài khoản thành công! đăng nhập thôi nào...");
            return "redirect:/initIndexAddUser.htm";
        } else {
            model.addAttribute("notificationsError", "có lỗi. Đã có lỗi khi đăng ký!");
            return "addIndexUser";
        }
    }
    
    @RequestMapping(value = "/updateIndexUser")
    public String updateUser(@Valid @ModelAttribute("u") UsersInsert u, BindingResult result, RedirectAttributes attributes, Model model) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        if (result.hasErrors()) {
            return "updateIndexUser";
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
            attributes.addFlashAttribute("notificationsSuccessfully", "thành công!");
            return "redirect:/initIndexUpdateUser.htm?userID="+u.getUserID();
        } else {
            model.addAttribute("notificationsError", "thất bại, xin kiểm tra lại!");
            return "updateIndexUser";
        }
    }
    @RequestMapping(value = "/initfindClient")
    public String initfindProduct(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            return "redirect:/findClient.htm?name=" + name + "&&page=1";
        } //Đặt điều kiện khi ô find bỏ trống
        else {
            return "redirect:/productClient.htm?name=" + "" + "&&page=1";
        }
    }

    @RequestMapping(value = "/findClient")
    public String findClient(@RequestParam("name") String name, @RequestParam("page") int currentPage, Model model) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.CountFind(name, 2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());

        int back = 4;
        model.addAttribute("listProduct", productsDAO.getProductsFindByName(name, currentPage, recordsPerPage, 2));
        model.addAttribute("currentName", name);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("link", "findClient");
        model.addAttribute("back", back);
        return "findClient";

    }

    @RequestMapping(value = "/findClientSortNameDesc")
    public String findClientSortNameDesc(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int back = 5;

        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.CountFind(name, 2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());

        model.addAttribute("back", back);
        model.addAttribute("link", "findClientSortNameDesc");
        model.addAttribute("currentName", name);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listProduct", productsDAO.getProductsFindByNameSortNameDesc(name, currentPage, recordsPerPage));
        return "findClient";
    }

    @RequestMapping(value = "/findClientSortNameAsc")
    public String findClientSortNameAsc(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int back = 6;
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.CountFind(name, 2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());

        model.addAttribute("back", back);
        model.addAttribute("link", "findClientSortNameAsc");
        model.addAttribute("currentName", name);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listProduct", productsDAO.getProductsFindByNameSortNameAsc(name, currentPage, recordsPerPage));
        return "findClient";
    }

    @RequestMapping(value = "/findClientSortPriceDesc")
    public String findClientSortPriceDesc(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int back = 7;

        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.CountFind(name, 2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());

        model.addAttribute("back", back);
        model.addAttribute("link", "findClientSortPriceDesc");
        model.addAttribute("currentName", name);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listProduct", productsDAO.getProductsFindByNameSortPriceDesc(name, currentPage, recordsPerPage));
        return "findClient";
    }

    @RequestMapping(value = "/findClientSortPriceAsc")
    public String findClientSortPriceAsc(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int back = 8;
        //Số sản phẩm mỗi trang
        int recordsPerPage = 12;
        //Tính tổng số trang và làm tròn
        long count = productsDAO.CountFind(name, 2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());

        model.addAttribute("back", back);
        model.addAttribute("link", "findClientSortPriceAsc");
        model.addAttribute("currentName", name);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listProduct", productsDAO.getProductsFindByNameSortPriceAsc(name, currentPage, recordsPerPage));
        return "findClient";
    }
}
