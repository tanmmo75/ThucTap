/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.validation.constraints.Size;

public class FootersInsert implements Serializable {

    private Integer footerID;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String name;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String content;
    private Boolean status;

    public FootersInsert() {
    }

    public FootersInsert(Integer footerID, String name, String content, Boolean status) {
        this.footerID = footerID;
        this.name = name;
        this.content = content;
        this.status = status;
    }

    public Integer getFooterID() {
        return footerID;
    }

    public void setFooterID(Integer footerID) {
        this.footerID = footerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
