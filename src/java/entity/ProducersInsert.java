/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.validation.constraints.Size;


public class ProducersInsert implements Serializable {

    private Integer producerID;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String name;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 1073741823 ký tự")
    private String logo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String phone;

    public ProducersInsert() {
    }

    public ProducersInsert(Integer producerID, String name, String logo, String email, String phone) {
        this.producerID = producerID;
        this.name = name;
        this.logo = logo;
        this.email = email;
        this.phone = phone;
    }

    public Integer getProducerID() {
        return producerID;
    }

    public void setProducerID(Integer producerID) {
        this.producerID = producerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
