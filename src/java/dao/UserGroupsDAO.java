/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.UserGroups;
import java.util.List;

public interface UserGroupsDAO {

    public List<UserGroups> getAllUserGroups();

    public UserGroups getUserGroupById(Integer id);

    public Boolean insertUserGroup(UserGroups ug);

    public Boolean updateUserGroup(UserGroups ug);

    public Boolean deleteUserGroup(Integer id);

    public List<UserGroups> getAllUserGroupsDropDownList();
}
