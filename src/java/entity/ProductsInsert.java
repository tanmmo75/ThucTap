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


public class ProductsInsert implements Serializable {

    private Integer productID;
    @Size(max = 250, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 250 ký tự")
    private String name;
    @Size(max = 10, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 10 ký tự")
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull(message = "Yêu cầu nhập thông tin")
    //private Double price;
   // @NotNull(message = "Yêu cầu nhập thông tin")
   // private Double discount;
    //@Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String image;
    @NotNull(message = "Yêu cầu nhập thông tin")
    private String description;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String detail;
    @NotNull(message = "Yêu cầu nhập thông tin")
    private Boolean special;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    @NotNull(message = "Yêu cầu nhập thông tin")
    private Integer views;
    private Boolean status;
    private Integer categoryID;
    private Integer producerID;
    private Integer userID;

    public ProductsInsert() {
    }

    public ProductsInsert(Integer productID, String name, String code, String image, String description, String detail, Boolean special, Date createDate, Integer views, Boolean status, Integer categoryID, Integer producerID, Integer userID) {
        this.productID = productID;
        this.name = name;
        this.code = code;
        this.image = image;
        this.description = description;
        this.detail = detail;
        this.special = special;
        this.createDate = createDate;
        this.views = views;
        this.status = status;
        this.categoryID = categoryID;
        this.producerID = producerID;
        this.userID = userID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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


    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getProducerID() {
        return producerID;
    }

    public void setProducerID(Integer producerID) {
        this.producerID = producerID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}
