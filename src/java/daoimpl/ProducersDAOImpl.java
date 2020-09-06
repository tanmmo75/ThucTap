/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.ProducersDAO;
import entity.Producers;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ProducersDAOImpl implements ProducersDAO {

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
            query = session.createQuery("Select count (ProducerID) from Producers");
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
            Query query = session.createQuery("Select count (ProducerID) from Producers where Name like '%'+:name+'%'");
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
    public List<Producers> getAllProducers(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Producers");
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
    public Producers getProducerById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Producers e = (Producers) session.get(Producers.class, id);
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
    public Boolean insertProducer(Producers pro) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(pro);
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
    public Boolean updateProducer(Producers pro) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(pro);
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
    public Boolean deleteProducer(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getProducerById(id));
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
    public List<Producers> getAllProducersDropDownList() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from Producers").list();
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
    public List<Producers> getProducersFindByName(String name, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Producers where Name like '%'+:name+'%'");
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

}
