package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;
import java.util.ArrayList;

import FutaBus.bean.LoaiXe;
import FutaBus.bean.TinhThanh;

@Repository
public class TinhThanhDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();

    public List<TinhThanh> getTinhThanhByPage(int offset, int limit) {
        List<TinhThanh> tinhThanhList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from TinhThanh";
            Query<TinhThanh> query = session.createQuery(hql, TinhThanh.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            tinhThanhList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tinhThanhList;
    }

    public long getTotalTinhThanh() {
        long total = 0;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();

            String hql = "select count(*) from TinhThanh";
            Query<Long> query = session.createQuery(hql, Long.class);
            total = query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return total;
    }
    
    public List<TinhThanh> getAllTinhThanh() {
        List<TinhThanh> tinhThanhList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from TinhThanh"; 
            Query<TinhThanh> query = session.createQuery(hql, TinhThanh.class);

            tinhThanhList = query.getResultList(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tinhThanhList;
    }
}
