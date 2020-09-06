/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.OrdersDAO;
import entity.Categories;
import entity.OrderDetails;
import entity.Orders;
import entity.Producers;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OrdersDAOImpl implements OrdersDAO {

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
                query = session.createQuery("Select count (OrderID) from Orders");
                long count = (long) query.uniqueResult();
                return count;
            }
            //Đếm tổng số admin status = 1
            if (mode == 2) {
                query = session.createQuery("Select count (OrderID) from Orders where Status = 1");
                long count = (long) query.uniqueResult();
                return count;
            }
            //Đếm tổng số admin status = 2
            if (mode == 3) {
                query = session.createQuery("Select count (OrderID) from Orders where Status = 2");
                long count = (long) query.uniqueResult();
                return count;
            }
            //Đếm tổng số admin status = 3
            if (mode == 4) {
                query = session.createQuery("Select count (OrderID) from Orders where Status = 3");
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

//    @Override
//    public long CountFind(String name, Integer mode) {
//        Session session = sessionFactory.openSession();
//        try {
//            session.beginTransaction();
//            //Đếm tổng số Admin
//            if (mode == 1) {
//                Query query = session.createQuery("Select count (OrderID) from Orders where Name like '%'+:name+'%'");
//                query.setString("name", name);
//                long count = (long) query.uniqueResult();
//                return count;
//            }
//            //Đếm tổng số Admin status = 1
//            if (mode == 2) {
//                Query query = session.createQuery("Select count (OrderID) from Orders where Name like '%'+:name+'%' and Status = 1");
//                query.setString("name", name);
//                long count = (long) query.uniqueResult();
//                return count;
//            }
//            //Đếm tổng số Admin status = 2
//            if (mode == 3) {
//                Query query = session.createQuery("Select count (OrderID) from Orders where Name like '%'+:name+'%' and Status = 2");
//                query.setString("name", name);
//                long count = (long) query.uniqueResult();
//                return count;
//            }
//            //Đếm tổng số Admin status = 3
//            if (mode == 4) {
//                Query query = session.createQuery("Select count (OrderID) from Orders where Name like '%'+:name+'%' and Status = 3");
//                query.setString("name", name);
//                long count = (long) query.uniqueResult();
//                return count;
//            }
//            session.getTransaction().commit();
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        }
//        return 1;
//    }

    @Override
    public List<Orders> getAllOrders(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Orders ORDER BY OrderID DESC");
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
    public Orders getOrderById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Orders e = (Orders) session.get(Orders.class, id);
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
    public Boolean insertOrder(Orders o) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(o);
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
    public Boolean updateOrder(Orders o) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(o);
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
    public Boolean deleteOrder(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getOrderById(id));
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
    public List<Orders> getAllStatus1(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Orders where Status = 1 ORDER BY OrderID DESC");
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
    public List<Orders> getAllStatus2(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Orders where Status = 2 ORDER BY OrderID DESC");
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
    public List<Orders> getAllStatus3(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Orders where Status = 3 ORDER BY OrderID DESC");
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
    public Double getReportOrderTotalPrices() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            double total = (double) session.createQuery("select sum(od.price * od.quantity) FROM OrderDetails od WHERE od.orders.status = 3 AND (od.orders.createDate BETWEEN '2018-11-01' AND GETDATE())").uniqueResult();
            session.getTransaction().commit();
            session.close();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 0.0;
    }

    @Override
    public Integer getReportSumTotalDonHangChuaHoanThanh() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from Orders where Status = 1").list();
            session.getTransaction().commit();
            session.close();
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 0;
    }

    @Override
    public Integer getReportSumTotalDonHangDaHoanThanh() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from Orders where Status = 3").list();
            session.getTransaction().commit();
            session.close();
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 0;
    }

    @Override
    public Integer getReportSumTotalDonHangDangVanChuyen() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from Orders where Status = 2").list();
            session.getTransaction().commit();
            session.close();
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 0;
    }

    @Override
    public Integer getReportSumTotalProduct() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("FROM Products WHERE Status = 0 AND Quantity > 0").list();
            session.getTransaction().commit();
            session.close();
            return list.size();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 0;
    }

    @Override
    public List<OrderDetails> getOrderByListId(Integer orderID) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            //SELECT od.products.image as productImage, od.products.name as productName 
            Query query = session.createQuery("FROM OrderDetails where orders.orderID = :orderID");
            query.setInteger("orderID", orderID);
            List<OrderDetails> list = (List<OrderDetails>) query.list();
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
    public Double getReportOrderTotalSum(Integer orderID) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select sum(od.price * od.quantity - od.discount * od.quantity) FROM OrderDetails od WHERE od.orders.orderID = :orderID");
            query.setInteger("orderID", orderID);
            double total = (double) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return 0.0;
    }

    // xử lý biểu đồ chartjs 
    @Override
    public List<Categories> getReportChartjs() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Categories");
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
    public Integer getLastOrders() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Orders order by OrderID desc");
            query.setMaxResults(1);
            List<Orders> o = query.list();
            session.getTransaction().commit();
            session.close();
            return o.get(0).getOrderID();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public List<Producers> getReportProducersChartjs() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("From Producers");
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
