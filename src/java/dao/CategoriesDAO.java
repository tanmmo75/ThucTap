/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Categories;
import java.util.List;


public interface CategoriesDAO {

    public long Count();

    public long CountFind(String name);

    public List<Categories> getAllCategorys(Integer currentPage, Integer recordsPerPage);

    public Categories getCategoryById(Integer id);

    public Boolean insertCategory(Categories cate);

    public Boolean updateCategory(Categories cate);

    public Boolean deleteCategory(Integer id);

    public List<Categories> getAllCategorysDropDownList();

    public List<Categories> getCategorysFindByName(String name, Integer currentPage, Integer recordsPerPage);

    public List<Categories> getIndexCategorys();
}
