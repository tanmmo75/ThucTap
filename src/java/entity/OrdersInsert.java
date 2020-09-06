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


public class OrdersInsert implements Serializable {

    private Integer orderID;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String shipName;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String shipMobile;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String shipAdress;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String shipEmai;
    private Integer status;

    public OrdersInsert() {
    }

    public OrdersInsert(Integer orderID, Date createDate, String shipName, String shipMobile, String shipAdress, String shipEmai, Integer status) {
        this.orderID = orderID;
        this.createDate = createDate;
        this.shipName = shipName;
        this.shipMobile = shipMobile;
        this.shipAdress = shipAdress;
        this.shipEmai = shipEmai;
        this.status = status;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipMobile() {
        return shipMobile;
    }

    public void setShipMobile(String shipMobile) {
        this.shipMobile = shipMobile;
    }

    public String getShipAdress() {
        return shipAdress;
    }

    public void setShipAdress(String shipAdress) {
        this.shipAdress = shipAdress;
    }

    public String getShipEmai() {
        return shipEmai;
    }

    public void setShipEmai(String shipEmai) {
        this.shipEmai = shipEmai;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
