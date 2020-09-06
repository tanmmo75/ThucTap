/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author LeDung
 */
@Entity
@Table(name = "Footers")
public class Footers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FooterID")
    private Integer footerID;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Name")
    private String name;
    @Size(max = 1073741823, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Content")
    private String content;
    @Column(name = "Status")
    private Boolean status;

    public Footers() {
    }

    public Footers(Integer footerID) {
        this.footerID = footerID;
    }

    public Integer getFooterID() {
        return footerID;
    }

    public void setFooterID(Integer footerID) {
        this.footerID = footerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        hash += (footerID != null ? footerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Footers)) {
            return false;
        }
        Footers other = (Footers) object;
        if ((this.footerID == null && other.footerID != null) || (this.footerID != null && !this.footerID.equals(other.footerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Footers[ footerID=" + footerID + " ]";
    }

}
