/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SlidesDAO;
import entity.Slides;
import entity.SlidesInsert;
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
public class SlidesController {

    private SlidesDAO slidesDAO;

    @Autowired
    public void setSlidesDAO(SlidesDAO slidesDAO) {
        this.slidesDAO = slidesDAO;
    }

    @RequestMapping(value = "/listS")
    public String listSlides(Model model) {
        model.addAttribute("listSlide", slidesDAO.getAllSlides());
        return "listSlide";
    }

    @RequestMapping(value = "/detailSlide")
    public String detail(@RequestParam("slideID") Integer slideID, Model model) {
        model.addAttribute("e", slidesDAO.getSlideById(slideID));
        return "detailSlide";
    }

    @RequestMapping(value = "/initAddSlide")
    public String initAdd(Model model) {
        model.addAttribute("s", new Slides());
        return "addSlide";
    }

    @RequestMapping(value = "/addSlide")
    public String insertSlide(@Valid @ModelAttribute("s") SlidesInsert s, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "addSlide";
        }
        Slides c = new Slides();
        c.setImage(s.getImage().substring(1));
        c.setDisplayOrder(s.getDisplayOrder());
        c.setCreateDate(s.getCreateDate());
        c.setStatus(s.getStatus());
        Boolean blInsert = slidesDAO.insertSlide(c);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listS.htm";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addSlide";
        }
    }

    @RequestMapping(value = "/initUpdateSlide")
    public String initUpdate(@RequestParam("slideID") Integer slideID, Model model) {
        Slides objA = slidesDAO.getSlideById(slideID);
        model.addAttribute("s", objA);
        return "updateSlide";
    }

    @RequestMapping(value = "/updateSlide")
    public String updateSlide(@Valid @ModelAttribute("s") SlidesInsert s, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "updateSlide";
        }
        Slides c = new Slides();
        c.setSlideID(s.getSlideID());
        c.setImage(s.getImage().substring(1));
        c.setDisplayOrder(s.getDisplayOrder());
        c.setCreateDate(s.getCreateDate());
        c.setStatus(s.getStatus());
        Boolean blUpdate = slidesDAO.updateSlide(c);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listS.htm";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateSlide";
        }
    }

    @RequestMapping(value = "/deleteSlide")
    public String deleteEmp(@RequestParam("slideID") Integer slideID, Model model) {
        Boolean blDelete = slidesDAO.deleteSlide(slideID);
        if (blDelete) {
            model.addAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        model.addAttribute("listSlide", slidesDAO.getAllSlides());
        return "listSlide";
    }
}
