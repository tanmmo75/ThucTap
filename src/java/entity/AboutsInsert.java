/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class AboutsInsert implements Serializable {

    private Integer aboutID;
    @Size(max = 250, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 250 ký tự")
    private String name;
    @Size(max = 500, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 500 ký tự")
    private String description;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String image;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String detail;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    private Boolean status;

    public AboutsInsert() {
    }

    public AboutsInsert(Integer aboutID, String name, String description, String image, String detail, Date createDate, Boolean status) {
        this.aboutID = aboutID;
        this.name = name;
        this.description = description;
        this.image = image;
        this.detail = detail;
        this.createDate = createDate;
        this.status = status;
    }

    public Integer getAboutID() {
        return aboutID;
    }

    public void setAboutID(Integer aboutID) {
        this.aboutID = aboutID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
