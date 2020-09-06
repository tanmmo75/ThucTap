/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.ContentsDAO;
import entity.Contents;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ContentsDAOImpl implements ContentsDAO {

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
            query = session.createQuery("Select count (ContentID) from Contents");
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
    public long CountFind(String Title) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            //Đếm tổng số sp tim kiem Admin
            Query query = session.createQuery("Select count (ContentID) from Contents where Title like '%'+:Title+'%'"); 
            query.setString("Title", Title);                                                                              
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
    public List<Contents> getAllContents(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Contents ORDER BY ContentID DESC");
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
    public Contents getContentById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Contents e = (Contents) session.get(Contents.class, id);
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
    public Boolean insertContent(Contents co) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(co);
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
    public Boolean updateContent(Contents co) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(co);
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
    public Boolean deleteContent(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getContentById(id));
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
    public List<Contents> getContentsFindByName(String contentName, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Contents where Title like '%'+:title+'%' ORDER BY ContentID DESC");
            query.setString("title", contentName);
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
