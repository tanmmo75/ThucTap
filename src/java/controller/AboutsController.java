package controller;

import dao.AboutsDAO;
import entity.Abouts;
import entity.AboutsInsert;
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

@Controller
public class AboutsController {

    private AboutsDAO aboutsDAO;

    @Autowired
    public void setAboutsDAO(AboutsDAO aboutsDAO) {
        this.aboutsDAO = aboutsDAO;
    }

    @RequestMapping(value = "/listA")
    public String listAbouts(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = aboutsDAO.Count();
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listAbout", aboutsDAO.getAllAbouts(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listAbout", aboutsDAO.getAllAbouts(currentPage, recordsPerPage));
        }
        return "listAbout";
    }

    @RequestMapping(value = "/initfindA")
    public String initfindAbout(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            return "redirect:/findA.htm?name=" + name + "&&page=1";
        } //Đặt điều kiện khi ô find bỏ trống
        else {
            return "redirect:/listA.htm?name=" + "" + "&&page=1";
        }
    }

    @RequestMapping(value = "/findA")
    public String findAbout(@RequestParam("name") String name, @RequestParam("page") int currentPage, Model model) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = aboutsDAO.CountFind(name);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentName", name);
        model.addAttribute("listAbout", aboutsDAO.getAboutsFindByName(name, currentPage, recordsPerPage));
        return "listAbout";
    }

    @RequestMapping(value = "/detailAbout")
    public String detail(@RequestParam("aboutID") Integer aboutID, Model model) {
        model.addAttribute("e", aboutsDAO.getAboutById(aboutID));
        return "detailAbout";
    }

    @RequestMapping(value = "/initAddAbout")
    public String initAdd(Model model) {
        model.addAttribute("a", new Abouts());
        return "addAbout";
    }

    @RequestMapping(value = "/addAbout")
    public String insertAbout(@Valid @ModelAttribute("a") AboutsInsert a, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "addAbout";
        }
        Abouts c = new Abouts();
        c.setName(a.getName());
        c.setDescription(a.getDescription());
        c.setImage(a.getImage().substring(1));
        c.setDetail(a.getDetail());
        c.setCreateDate(a.getCreateDate());
        c.setStatus(a.getStatus());
        Boolean blInsert = aboutsDAO.insertAbout(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listA.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addAbout";
        }
    }

    @RequestMapping(value = "/initUpdateAbout")
    public String initUpdate(@RequestParam("aboutID") Integer aboutID, Model model) {
        Abouts objA = aboutsDAO.getAboutById(aboutID);
        model.addAttribute("a", objA);
        return "updateAbout";
    }

    @RequestMapping(value = "/updateAbout")
    public String updateAbout(@Valid @ModelAttribute("a") AboutsInsert a, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "updateAbout";
        }
        Abouts c = new Abouts();
        c.setAboutID(a.getAboutID());
        c.setName(a.getName());
        c.setDescription(a.getDescription());
        c.setImage(a.getImage().substring(1));
        c.setDetail(a.getDetail());
        c.setCreateDate(a.getCreateDate());
        c.setStatus(a.getStatus());
        Boolean blUpdate = aboutsDAO.updateAbout(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listA.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateAbout";
        }
    }

    @RequestMapping(value = "/deleteAbout")
    public String deleteEmp(@RequestParam("aboutID") Integer aboutID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = aboutsDAO.deleteAbout(aboutID);
        if (blDelete) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            attributes.addFlashAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        String name = request.getParameter("find");
        String page = request.getParameter("page");
        if (name != null && !name.isEmpty()) {
            return "redirect:/findA.htm?name=" + name + "&&page=" + page;
        } else {
            return "redirect:/listA.htm?page=" + page;
        }
    }
}
