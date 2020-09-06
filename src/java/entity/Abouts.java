/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Abouts")
public class Abouts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AboutID")
    private Integer aboutID;
    @Size(max = 250, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Name")
    private String name;
    @Size(max = 500, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Description")
    private String description;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Image")
    private String image;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Detail")
    private String detail;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    @Column(name = "Status")
    private Boolean status;

    public Abouts() {
    }

    public Abouts(Integer aboutID) {
        this.aboutID = aboutID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aboutID != null ? aboutID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Abouts)) {
            return false;
        }
        Abouts other = (Abouts) object;
        if ((this.aboutID == null && other.aboutID != null) || (this.aboutID != null && !this.aboutID.equals(other.aboutID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Abouts[ aboutID=" + aboutID + " ]";
    }

}
