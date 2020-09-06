/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.FeedBacksDAO;
import entity.FeedBacks;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class FeedBacksDAOImpl implements FeedBacksDAO {

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
            //Đếm tổng số admin
            if (mode == 1) {
                query = session.createQuery("Select count (FeedBackID) from FeedBacks");
                long count = (long) query.uniqueResult();
                return count;
            }
            //Đếm tổng số admin status = 1
            if (mode == 2) {
                query = session.createQuery("Select count (FeedBackID) from FeedBacks where Status = 1");
                long count = (long) query.uniqueResult();
                return count;
            }
            //Đếm tổng số admin status = 2
            if (mode == 3) {
                query = session.createQuery("Select count (FeedBackID) from FeedBacks where Status = 2");
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
    public List<FeedBacks> getAllFeedBacks(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from FeedBacks ORDER BY FeedBackID DESC");
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
    public FeedBacks getFeedBackById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            FeedBacks e = (FeedBacks) session.get(FeedBacks.class, id);
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
    public Boolean insertFeedBack(FeedBacks fb) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(fb);
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
    public Boolean updateFeedBack(FeedBacks fb) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(fb);
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
    public Boolean deleteFeedBack(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getFeedBackById(id));
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
    public List<FeedBacks> getFeedBacksFindByName(String feedbackName) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM FeedBacks where Name like '%'+:name+'%' ORDER BY FeedBackID DESC");
            query.setString("name", feedbackName);
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
    public List<FeedBacks> getAllStatus1(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from FeedBacks where Status = 0 ORDER BY FeedBackID DESC");
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
    public List<FeedBacks> getAllStatus2(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from FeedBacks where Status = 1 ORDER BY FeedBackID DESC");
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
