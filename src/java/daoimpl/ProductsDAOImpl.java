/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.ProductsDAO;
import entity.Products;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ProductsDAOImpl implements ProductsDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long Count(Integer mode) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query;
            //Đếm tổng số sp admin
            if (mode == 1) {
                query = session.createQuery("Select count (ProductID) from Products");
                long count = (long) query.uniqueResult();
                return count;
            }
            //Đếm tổng số sp client 
            if (mode == 2) {
                query = session.createQuery("Select count (ProductID) from Products where Status = 0");
                long count = (long) query.uniqueResult();
                return count;
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 1;
    }

    @Override
    public long CountbyCategory(Integer categoryID) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query;
            //Đếm tổng số sp admin
            query = session.createQuery("Select count (ProductID) from Products where CategoryID = :categoryID and Status = 0");
            query.setInteger("categoryID", categoryID);
            long count = (long) query.uniqueResult();

            session.getTransaction().commit();
            session.close();
            return count;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 1;
    }

    @Override
    public long CountFind(String name, Integer mode) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            //Đếm tổng số sp tim kiem Admin
            if (mode == 1) {
                Query query = session.createQuery("Select count (ProductID) from Products where Name like '%'+:name+'%'");
                query.setString("name", name);
                long count = (long) query.uniqueResult();
                return count;
            }
            //Đếm tổng số sp tim kiem Client
            if (mode == 2) {
                Query query = session.createQuery("Select count (ProductID) from Products where Name like '%'+:name+'%' and Status = 0");
                query.setString("name", name);
                long count = (long) query.uniqueResult();
                return count;
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 1;
    }

    @Override
    public List<Products> getAllProducts(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Products ORDER BY ProductID DESC");

            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);

            List list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getProductsFindByName(String name, Integer currentPage, Integer recordsPerPage, Integer mode) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            //Find admin
            if (mode == 1) {
                Query query = session.createQuery("FROM Products where Name like '%'+:name+'%' ORDER BY ProductID DESC");
                query.setString("name", name);
                //Pagination
                int offset = (currentPage - 1) * recordsPerPage;
                query.setFirstResult(offset);
                query.setMaxResults(recordsPerPage);
                List list = query.list();
                return list;

            }
            //Find client
            if (mode == 2) {
                Query query = session.createQuery("FROM Products where Name like '%'+:name+'%' and Status = 0 ORDER BY ProductID DESC");
                query.setString("name", name);
                //Pagination
                int offset = (currentPage - 1) * recordsPerPage;
                query.setFirstResult(offset);
                query.setMaxResults(recordsPerPage);
                List list = query.list();
                return list;

            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Products getProductById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Products e = (Products) session.get(Products.class, id);
            session.getTransaction().commit();
            session.close();
            return e;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Boolean insertProduct(Products p) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public Boolean updateProduct(Products p) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(p);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getProductById(id));
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public List<Products> getIndexProducts() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Status = 0 ORDER BY ProductID DESC");
            query.setMaxResults(9);
            List<Products> list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getIndexHotProducts() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products WHERE Status = 0 and Special = 0 ORDER BY ProductID Desc");
            query.setMaxResults(4);
            List<Products> list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getIndexTopViewProducts() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Status = 0 and Views > 0 ORDER BY ProductID DESC");
            query.setMaxResults(3);
            List<Products> list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Products getProductsByCategory(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Products> getProductsByCategorys(Integer categoryID, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where CategoryID = :categoryID and Status = 0 ORDER BY ProductID DESC");
            query.setInteger("categoryID", categoryID);
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List<Products> list = (List<Products>) query.list();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getProductsRelated(Integer categoryID, Integer productID) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Products Where categoryID.categoryID = :categoryID and productID <> :productID order by ProductID DESC");
            query.setInteger("categoryID", categoryID);
            query.setInteger("productID", productID);
            query.setMaxResults(9);
            List<Products> list = (List<Products>) query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getAllProductsSortNameDesc(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Products where Status = 0 ORDER BY Name DESC");
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getProductsFindByNameSortNameDesc(String name, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Name like '%'+:name+'%' and Status = 0 ORDER BY Name DESC");
            query.setString("name", name);
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();

            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getAllProductsSortNameAsc(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Status = 0 ORDER BY Name ASC");
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getProductsFindByNameSortNameAsc(String name, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Name like '%'+:name+'%' and Status = 0 ORDER BY Name ASC");
            query.setString("name", name);
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getAllProductsSortPriceDesc(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Status = 0 ORDER BY Price DESC");
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getProductsFindByNameSortPriceDesc(String name, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Name like '%'+:name+'%' and Status = 0 ORDER BY Price DESC");
            query.setString("name", name);
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();

            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getAllProductsSortPriceAsc(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Status = 0 ORDER BY Price ASC");
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();
            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getProductsFindByNameSortPriceAsc(String name, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Products where Name like '%'+:name+'%' and Status = 0 ORDER BY Price ASC");
            query.setString("name", name);
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();

            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Products> getAllIndexProducts(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Products where Status = 0 ORDER BY ProductID DESC");
            //Pagination
            int offset = (currentPage - 1) * recordsPerPage;
            query.setFirstResult(offset);
            query.setMaxResults(recordsPerPage);
            List list = query.list();

            session.getTransaction().commit();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

}
