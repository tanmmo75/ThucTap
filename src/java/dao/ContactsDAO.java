/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Contacts;
import java.util.List;


public interface ContactsDAO {
    public List<Contacts> getAllContacts();
    public Contacts getContactById(Integer id);
    public Boolean insertContact(Contacts c);
    public Boolean updateContact(Contacts c);
    public Boolean deleteContact(Integer id);
}
