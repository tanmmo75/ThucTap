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

public class ContentsInsert implements Serializable {

    private Integer contentID;
    @Size(max = 255, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 255 ký tự")
    private String title;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String image;
    @Size(max = 500, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 500 ký tự")
    private String description;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String detail;
    @Size(max = 255, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 255 ký tự")
    private String contenSource;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    private Boolean status;
    private Integer userID;

    public ContentsInsert() {
    }

    public ContentsInsert(Integer contentID, String title, String image, String description, String detail, String contenSource, Date createDate, Boolean status, Integer userID) {
        this.contentID = contentID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.detail = detail;
        this.contenSource = contenSource;
        this.createDate = createDate;
        this.status = status;
        this.userID = userID;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getContenSource() {
        return contenSource;
    }

    public void setContenSource(String contenSource) {
        this.contenSource = contenSource;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}
