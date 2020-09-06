/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriesDAO;
import dao.ContactsDAO;
import dao.FeedBacksDAO;
import dao.FootersDAO;
import dao.ProductsDAO;
import entity.FeedBacks;
import entity.FeedBacksInsert;
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
public class FeedBacksController {

    private FeedBacksDAO feedBacksDAO;
    private ProductsDAO productsDAO;
    private CategoriesDAO categoriesDAO;
    private FootersDAO footersDAO;
    private ContactsDAO contactsDAO;

    @Autowired
    public void setContactsDAO(ContactsDAO contactsDAO) {
        this.contactsDAO = contactsDAO;
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
    public void setFeedBacksDAO(FeedBacksDAO feedBacksDAO) {
        this.feedBacksDAO = feedBacksDAO;
    }

    @RequestMapping(value = "/listFB")
    public String listFeedBacks(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = feedBacksDAO.Count(1);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listFeedBack", feedBacksDAO.getAllFeedBacks(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listFeedBack", feedBacksDAO.getAllFeedBacks(currentPage, recordsPerPage));
        }
        return "listFeedBack";
    }

    @RequestMapping(value = "/findFB")
    public String findFeedBack(@RequestParam("name") String name, Model model) {
        model.addAttribute("fb", new FeedBacks());
        if (name != null && !name.isEmpty()) {
            model.addAttribute("listFeedBack", feedBacksDAO.getFeedBacksFindByName(name));
            model.addAttribute("currentName", name);
            return "listFeedBack";
        } else {
            return "redirect:/listFB.htm";
        }
    }

    @RequestMapping(value = "/detailFeedBack")
    public String detail(@RequestParam("feedBackID") Integer feedBackID, Model model) {
        model.addAttribute("e", feedBacksDAO.getFeedBackById(feedBackID));
        return "detailFeedBack";
    }

    @RequestMapping(value = "/initAddFeedBack")
    public String initAdd(Model model) {
        model.addAttribute("fb", new FeedBacks());
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listContact", contactsDAO.getAllContacts());
        return "addFeedBack";
    }

    @RequestMapping(value = "/addFeedBack")
    public String insertFeedBack(@Valid @ModelAttribute("fb") FeedBacksInsert fb, BindingResult result, RedirectAttributes attributes, Model model) {
        model.addAttribute("listView", productsDAO.getIndexTopViewProducts());
        model.addAttribute("listC", categoriesDAO.getIndexCategorys());
        model.addAttribute("listF", footersDAO.getIndexFooters());
        model.addAttribute("listContact", contactsDAO.getAllContacts());
        if (result.hasErrors()) {
            return "contactClient";
        }
        FeedBacks c = new FeedBacks();
        c.setName(fb.getName());
        c.setPhone(fb.getPhone());
        c.setEmail(fb.getEmail());
        c.setAddress(fb.getAddress());
        c.setContent(fb.getContent());
        c.setCreateDate(fb.getCreateDate());
        c.setStatus(fb.getStatus());
        Boolean blInsert = feedBacksDAO.insertFeedBack(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Phản hồi của bạn đã được gửi thành công! Xin chân thành cảm ơn sự đóng góp ý kiến của bạn trường học!");
            return "redirect:/contactClient.htm";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi xảy ra trong quá trình nhập. Vui lòng kiểm tra lại!");
            return "contactClient";
        }
    }

    @RequestMapping(value = "/initUpdateFeedBack")
    public String initUpdate(@RequestParam("feedBackID") Integer feedBackID, Model model) {
        FeedBacks objA = feedBacksDAO.getFeedBackById(feedBackID);
        model.addAttribute("fb", objA);
        return "updateFeedBack";
    }

    @RequestMapping(value = "/updateFeedBack")
    public String updateFeedBack(@Valid @ModelAttribute("fb") FeedBacksInsert fb, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "updateFeedBack";
        }
        FeedBacks c = new FeedBacks();
        c.setFeedBackID(fb.getFeedBackID());
        c.setName(fb.getName());
        c.setPhone(fb.getPhone());
        c.setEmail(fb.getEmail());
        c.setAddress(fb.getAddress());
        c.setContent(fb.getContent());
        c.setCreateDate(fb.getCreateDate());
        c.setStatus(fb.getStatus());
        Boolean blUpdate = feedBacksDAO.updateFeedBack(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listFB.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateFeedBack";
        }
    }

    @RequestMapping(value = "/deleteFeedBack")
    public String deleteEmp(@RequestParam("feedBackID") Integer feedBackID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = feedBacksDAO.deleteFeedBack(feedBackID);
        if (blDelete) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            attributes.addFlashAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        int status = Integer.parseInt(request.getParameter("status"));
        String page = request.getParameter("page");
        if (status == 1) {
            return "redirect:/listFeedBacksStatus1.htm?page=" + page;
        } else if (status == 2) {
            return "redirect:/listFeedBacksStatus2.htm?page=" + page;
        } else {
            return "redirect:/listFB.htm?page=" + page;
        }
    }

    @RequestMapping(value = "/listFeedBacksStatus1")
    public String listFeedBacksStatus1(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = feedBacksDAO.Count(2);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listFeedBack", feedBacksDAO.getAllStatus1(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listFeedBack", feedBacksDAO.getAllStatus1(currentPage, recordsPerPage));
        }
        model.addAttribute("status", 1);
        return "listFeedBack";
    }

    @RequestMapping(value = "/listFeedBacksStatus2")
    public String listFeedBacksStatus2(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = feedBacksDAO.Count(3);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listFeedBack", feedBacksDAO.getAllStatus2(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listFeedBack", feedBacksDAO.getAllStatus2(currentPage, recordsPerPage));
        }
        model.addAttribute("status", 2);
        return "listFeedBack";
    }
}
