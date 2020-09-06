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
@Table(name = "Slides")
public class Slides implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SlideID")
    private Integer slideID;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Image")
    private String image;
    @Column(name = "DisplayOrder")
    private Integer displayOrder;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày tháng không hợp lệ")
    private Date createDate;
    @Column(name = "Status")
    private Boolean status;

    public Slides() {
    }

    public Slides(Integer slideID) {
        this.slideID = slideID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slideID != null ? slideID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slides)) {
            return false;
        }
        Slides other = (Slides) object;
        if ((this.slideID == null && other.slideID != null) || (this.slideID != null && !this.slideID.equals(other.slideID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Slides[ slideID=" + slideID + " ]";
    }
    
}
