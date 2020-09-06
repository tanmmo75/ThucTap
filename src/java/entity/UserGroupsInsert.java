/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.validation.constraints.Size;


public class UserGroupsInsert implements Serializable {

    private Integer userGroupID;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String name;
    @Size(max = 50, min = 1, message = "Yêu cầu nhập thông tin và tối đa là 50 ký tự")
    private String description;

    public UserGroupsInsert() {
    }

    public UserGroupsInsert(Integer userGroupID, String name, String description) {
        this.userGroupID = userGroupID;
        this.name = name;
        this.description = description;
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

}
