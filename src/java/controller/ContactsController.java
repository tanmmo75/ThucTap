/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContactsDAO;
import entity.Contacts;
import entity.ContactsInsert;
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
public class ContactsController {

    private ContactsDAO contactsDAO;

    @Autowired
    public void setContactsDAO(ContactsDAO contactsDAO) {
        this.contactsDAO = contactsDAO;
    }

    @RequestMapping(value = "/listC")
    public String listContacts(Model model) {
        model.addAttribute("listContact", contactsDAO.getAllContacts());
        return "listContact";
    }

    @RequestMapping(value = "/detailContact")
    public String detail(@RequestParam("contactID") Integer contactID, Model model) {
        model.addAttribute("e", contactsDAO.getContactById(contactID));
        return "detailContact";
    }

    @RequestMapping(value = "/initAddContact")
    public String initAdd(Model model) {
        model.addAttribute("c", new Contacts());
        return "addContact";
    }

    @RequestMapping(value = "/addContact")
    public String insertContact(@Valid @ModelAttribute("c") ContactsInsert c, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "addContact";
        }
        Contacts c1 = new Contacts();
        c1.setContent(c.getContent());
        c1.setStatus(c.getStatus());
        Boolean blInsert = contactsDAO.insertContact(c1);
        if (blInsert) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Thêm mới dữ liệu thành công!");
            return "redirect:/listC.htm";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi thêm dữ liệu!");
            return "addContact";
        }
    }

    @RequestMapping(value = "/initUpdateContact")
    public String initUpdate(@RequestParam("contactID") Integer contactID, Model model) {
        Contacts objA = contactsDAO.getContactById(contactID);
        model.addAttribute("c", objA);
        return "updateContact";
    }

    @RequestMapping(value = "/updateContact")
    public String updateContact(@Valid @ModelAttribute("c") ContactsInsert c, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            return "updateContact";
        }
        Contacts c1 = new Contacts();
        c1.setContactID(c.getContactID());
        c1.setContent(c.getContent());
        c1.setStatus(c.getStatus());
        Boolean blUpdate = contactsDAO.updateContact(c1);
        if (blUpdate) {
            attributes.addFlashAttribute("notificationsSuccessfully", "Cập nhật dữ liệu thành công!");
            return "redirect:/listC.htm";
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi cập nhật dữ liệu!");
            return "updateContact";
        }
    }

    @RequestMapping(value = "/deleteContact")
    public String deleteEmp(@RequestParam("contactID") Integer contactID, Model model) {
        Boolean blDelete = contactsDAO.deleteContact(contactID);
        if (blDelete) {
            model.addAttribute("notificationsSuccessfully", "Xóa dữ liệu thành công!");
        } else {
            model.addAttribute("notificationsError", "Đã có lỗi khi xóa dữ liệu");
        }
        model.addAttribute("listContact", contactsDAO.getAllContacts());
        return "listContact";
    }
}
