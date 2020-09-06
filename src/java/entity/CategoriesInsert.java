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


public class CategoriesInsert implements Serializable {

    private Integer categoryID;
    @Size(max = 250, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 250 ký tự")
    private String name;
    @NotNull(message = "Yêu cầu nhập thông tin và ký tự nhập là số")
    private Integer parenId;
    @NotNull(message = "Yêu cầu nhập thông tin và ký tự nhập là số")
    private Integer displayOrder;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    private Boolean status;
    private Integer userID;

    public CategoriesInsert() {
    }

    public CategoriesInsert(Integer categoryID, String name, Integer parenId, Integer displayOrder, Date createDate, Boolean status, Integer userID) {
        this.categoryID = categoryID;
        this.name = name;
        this.parenId = parenId;
        this.displayOrder = displayOrder;
        this.createDate = createDate;
        this.status = status;
        this.userID = userID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParenId() {
        return parenId;
    }

    public void setParenId(Integer parenId) {
        this.parenId = parenId;
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

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

}
