/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.UsersDAO;
import entity.Users;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class UsersDAOImpl implements UsersDAO {

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
            query = session.createQuery("Select count (UserID) from Users");
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
            Query query = session.createQuery("Select count (UserID) from Users Username Name like '%'+:name+'%'");
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
    public List<Users> getAllUsers(Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Users ORDER BY UserID DESC");
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
    public Users getUserById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Users e = (Users) session.get(Users.class, id);
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
    public Boolean insertUser(Users u) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(u);
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
    public Boolean updateUser(Users u) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(u);
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
    public Boolean deleteUser(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getUserById(id));
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
    public List<Users> getAllUsersDropDownList() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List list = session.createQuery("from Users where Status = '0'").list();
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
    public List<Users> getUsersFindByName(String userName, Integer currentPage, Integer recordsPerPage) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Users where Username like '%'+:name+'%' ORDER BY UserID DESC");
            query.setString("name", userName);
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
    public Users getUsersLogin(String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Users Where username = :username and password = :password and (userGroupID.userGroupID = 4 OR userGroupID.userGroupID = 6) and Status = 0");
            query.setString("username", username);
            query.setString("password", password);
            Users u = (Users) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Users getUsersLoginClient(String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from Users Where username = :username and password = :password and userGroupID.userGroupID = 5 and Status = 0");
            query.setString("username", username);
            query.setString("password", password);
            Users u = (Users) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

}
