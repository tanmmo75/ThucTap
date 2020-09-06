/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.OrderDetailsDAO;
import entity.OrderDetails;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from OrderDetails").list();
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
    public OrderDetails getOrderDetailById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            OrderDetails e = (OrderDetails) session.get(OrderDetails.class, id);
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
    public Boolean insertOrderDetail(OrderDetails od) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(od);
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
    public Boolean updateOrderDetail(OrderDetails od) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(od);
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
    public Boolean deleteOrderDetail(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getOrderDetailById(id));
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            session.getTransaction().rollback();
        }
        return false;
    }

}
