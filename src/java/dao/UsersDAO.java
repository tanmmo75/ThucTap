/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Users;
import java.util.List;


public interface UsersDAO {

    public long Count();

    public long CountFind(String name);

    public List<Users> getAllUsers(Integer currentPage, Integer recordsPerPage);

    public Users getUserById(Integer id);

    public Boolean insertUser(Users u);

    public Boolean updateUser(Users u);

    public Boolean deleteUser(Integer id);

    public List<Users> getAllUsersDropDownList();

    public List<Users> getUsersFindByName(String userName, Integer currentPage, Integer recordsPerPage);

    public Users getUsersLogin(String username, String password);

    public Users getUsersLoginClient(String username, String password);
}
