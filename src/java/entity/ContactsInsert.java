/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.validation.constraints.Size;


public class ContactsInsert implements Serializable {

    private Integer contactID;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String content;
    private Boolean status;

    public ContactsInsert() {
    }

    public ContactsInsert(Integer contactID, String content, Boolean status) {
        this.contactID = contactID;
        this.content = content;
        this.status = status;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
