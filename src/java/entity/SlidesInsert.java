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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


public class SlidesInsert implements Serializable {

    private Integer slideID;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String image;
    @NotNull(message = "Yêu cầu nhập thông tin")
    private Integer displayOrder;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    private Boolean status;

    public SlidesInsert() {
    }

    public SlidesInsert(Integer slideID, String image, Integer displayOrder, Date createDate, Boolean status) {
        this.slideID = slideID;
        this.image = image;
        this.displayOrder = displayOrder;
        this.createDate = createDate;
        this.status = status;
    }

    public Integer getSlideID() {
        return slideID;
    }

    public void setSlideID(Integer slideID) {
        this.slideID = slideID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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
