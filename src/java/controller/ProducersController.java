/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProducersDAO;
import entity.Producers;
import entity.ProducersInsert;
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
public class ProducersController {

    private ProducersDAO producersDAO;

    @Autowired
    public void setProducersDAO(ProducersDAO producersDAO) {
        this.producersDAO = producersDAO;
    }

    @RequestMapping(value = "/listPro")
    public String listProducers(Model model, HttpServletRequest request) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = producersDAO.Count();
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double để hoạt động
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);

        model.addAttribute("noOfPages", noOfPages);

        //Điều kiện để mặc định trang đầu tiên là trang 1
        if (request.getParameter("page") == null) {
            int currentPage = 1;
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProducer", producersDAO.getAllProducers(currentPage, recordsPerPage));
        } else {
            int currentPage = Integer.parseInt(request.getParameter("page"));
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProducer", producersDAO.getAllProducers(currentPage, recordsPerPage));
        }
        return "listProducer";
    }

    @RequestMapping(value = "/initfindPro")
    public String initfindProducer(String name, Model model) {
        if (name != null && !name.isEmpty()) {
            return "redirect:/findPro.htm?name=" + name + "&&page=1";
        } //Đặt điều kiện khi ô find bỏ trống
        else {
            return "redirect:/listPro.htm?name=" + "" + "&&page=1";
        }
    }

    @RequestMapping(value = "/findPro")
    public String findProducer(@RequestParam("name") String name, @RequestParam("page") int currentPage, Model model) {
        //Số sản phẩm mỗi trang
        int recordsPerPage = 10;
        //Tính tổng số trang và làm tròn
        long count = producersDAO.CountFind(name);
        //Math.ceil() cần ít nhất một giá trị tham gia phải là double
        int noOfPages = (int) Math.ceil(count / (double) recordsPerPage);
        model.addAttribute("noOfPages", noOfPages);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("currentName", name);
        model.addAttribute("listProducer", producersDAO.getProducersFindByName(name, currentPage, recordsPerPage));
        return "listProducer";
    }

    @RequestMapping(value = "/detailProducer")
    public String detail(@RequestParam("producerID") Integer producerID, Model model) {
        model.addAttribute("e", producersDAO.getProducerById(producerID));
        return "detailProducer";
    }

    @RequestMapping(value = "/initAddProducer")
    public String initAdd(Model model) {
        model.addAttribute("pro", new Producers());
        return "addProducer";
    }

    @RequestMapping(value = "/addProducer")
    public String insertProducer(@Valid @ModelAttribute("pro") ProducersInsert pro, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "addProducer";
        }
        Producers c = new Producers();
        c.setName(pro.getName());
        c.setLogo(pro.getLogo().substring(1));
        c.setEmail(pro.getEmail());
        c.setPhone(pro.getPhone());
        Boolean blInsert = producersDAO.insertProducer(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listPro.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addProducer";
        }
    }

    @RequestMapping(value = "/initUpdateProducer")
    public String initUpdate(@RequestParam("producerID") Integer producerID, Model model) {
        Producers objA = producersDAO.getProducerById(producerID);
        model.addAttribute("pro", objA);
        return "updateProducer";
    }

    @RequestMapping(value = "/updateProducer")
    public String updateProducer(@Valid @ModelAttribute("pro") ProducersInsert pro, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "updateProducer";
        }
        Producers c = new Producers();
        c.setProducerID(pro.getProducerID());
        c.setName(pro.getName());
        c.setLogo(pro.getLogo().substring(1));
        c.setEmail(pro.getEmail());
        c.setPhone(pro.getPhone());
        Boolean blUpdate = producersDAO.updateProducer(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listPro.htm?page=1";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateProducer";
        }
    }

    @RequestMapping(value = "/deleteProducer")
    public String deleteEmp(@RequestParam("producerID") Integer producerID, Model model, RedirectAttributes attributes, HttpServletRequest request) {
        Boolean blDelete = producersDAO.deleteProducer(producerID);
        if (blDelete) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            attributes.addFlashAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        String name = request.getParameter("find");
        String page = request.getParameter("page");
        if (name != null && !name.isEmpty()) {
            return "redirect:/findPro.htm?name=" + name + "&&page=" + page;
        } else {
            return "redirect:/listPro.htm?page=" + page;
        }
    }
}
