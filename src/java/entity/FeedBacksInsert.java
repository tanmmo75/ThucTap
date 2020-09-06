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


public class FeedBacksInsert implements Serializable {

    private Integer feedBackID;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String name;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String email;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String address;
    @Size(max = 250, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 250 ký tự")
    private String content;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    private Boolean status;

    public FeedBacksInsert() {
    }

    public FeedBacksInsert(Integer feedBackID, String name, String phone, String email, String address, String content, Date createDate, Boolean status) {
        this.feedBackID = feedBackID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.content = content;
        this.createDate = createDate;
        this.status = status;
    }

    public Integer getFeedBackID() {
        return feedBackID;
    }

    public void setFeedBackID(Integer feedBackID) {
        this.feedBackID = feedBackID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
