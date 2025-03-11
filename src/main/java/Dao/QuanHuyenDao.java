package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;
import java.util.ArrayList;
import FutaBus.bean.QuanHuyen;

@Repository
public class QuanHuyenDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();

    public List<QuanHuyen> getQuanHuyenByPage(int offset, int limit) {
        List<QuanHuyen> quanHuyenList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from QuanHuyen";
            Query<QuanHuyen> query = session.createQuery(hql, QuanHuyen.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            quanHuyenList = query.getResultList();
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
        return quanHuyenList;
    }

    public long getTotalQuanHuyen() {
        long total = 0;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();

            String hql = "select count(*) from QuanHuyen";
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
}
