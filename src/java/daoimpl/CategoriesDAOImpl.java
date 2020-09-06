/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.CategoriesDAO;
import entity.Categories;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CategoriesDAOImpl implements CategoriesDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long Count() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query;
            //Đếm tổng số sp admin
            query = session.createQuery("Select count (CategoryID) from Categories");
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
    public long CountFind(String name) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            //Đếm tổng số sp tim kiem Admin
            Query query = session.createQuery("Select count (CategoryID) from Categories where Name like '%'+:name+'%'");
            query.setString("name", name);
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
    public List<Categories> getAllCategorys(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Categories ORDER BY CategoryID DESC");
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
    public Categories getCategoryById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Categories e = (Categories) session.get(Categories.class, id);
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
    public Boolean insertCategory(Categories cate) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(cate);
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
    public Boolean updateCategory(Categories cate) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(cate);
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
    public Boolean deleteCategory(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getCategoryById(id));
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
    public List<Categories> getAllCategorysDropDownList() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from Categories where Status = '0'").list();
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
    public List<Categories> getCategorysFindByName(String name, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Categories where Name like '%'+:name+'%' ORDER BY CategoryID DESC");
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
    public List<Categories> getIndexCategorys() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from Categories where Status = 0 ORDER BY CategoryID DESC").list();
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
