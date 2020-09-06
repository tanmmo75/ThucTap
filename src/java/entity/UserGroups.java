/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "UserGroups")
public class UserGroups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserGroupID")
    private Integer userGroupID;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Name")
    private String name;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin")
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "userGroupID")
    private Collection<Users> usersCollection;

    public UserGroups() {
    }

    public UserGroups(Integer userGroupID) {
        this.userGroupID = userGroupID;
    }

    public Integer getUserGroupID() {
        return userGroupID;
    }

    public void setUserGroupID(Integer userGroupID) {
        this.userGroupID = userGroupID;
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

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userGroupID != null ? userGroupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroups)) {
            return false;
        }
        UserGroups other = (UserGroups) object;
        if ((this.userGroupID == null && other.userGroupID != null) || (this.userGroupID != null && !this.userGroupID.equals(other.userGroupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserGroups[ userGroupID=" + userGroupID + " ]";
    }

}
