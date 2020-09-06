/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Products;
import java.util.List;


public interface ProductsDAO {
    public long Count(Integer mode);
    
    public long CountbyCategory(Integer categoryID);
    
    public long CountFind(String name, Integer mode);

    public List<Products> getAllProducts(Integer currentPage, Integer recordsPerPage);
    
    public List<Products> getProductsFindByName(String name, Integer currentPage, Integer recordsPerPage, Integer mode);

    public List<Products> getProductsFindByNameSortNameDesc(String name, Integer currentPage, Integer recordsPerPage);

    public List<Products> getProductsFindByNameSortNameAsc(String name, Integer currentPage, Integer recordsPerPage);

    public List<Products> getProductsFindByNameSortPriceDesc(String name, Integer currentPage, Integer recordsPerPage);

    public List<Products> getProductsFindByNameSortPriceAsc(String name, Integer currentPage, Integer recordsPerPage);
    
    public Products getProductById(Integer id);

    public Boolean insertProduct(Products p);

    public Boolean updateProduct(Products p);

    public Boolean deleteProduct(Integer id);

    public List<Products> getIndexProducts();

    public List<Products> getIndexHotProducts();

    public List<Products> getIndexTopViewProducts();

    public Products getProductsByCategory(Integer id);

    public List<Products> getProductsByCategorys(Integer categoryID, Integer currentPage, Integer recordsPerPage);

    public List<Products> getProductsRelated(Integer categoryID, Integer productID);

    public List<Products> getAllProductsSortNameDesc(Integer currentPage, Integer recordsPerPage);

    public List<Products> getAllProductsSortNameAsc(Integer currentPage, Integer recordsPerPage);

    public List<Products> getAllProductsSortPriceDesc(Integer currentPage, Integer recordsPerPage);

    public List<Products> getAllProductsSortPriceAsc(Integer currentPage, Integer recordsPerPage);

    public List<Products> getAllIndexProducts(Integer currentPage, Integer recordsPerPage);
}
