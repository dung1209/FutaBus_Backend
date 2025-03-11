package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;
import java.util.ArrayList;
import FutaBus.bean.NguoiDung;

@Repository
public class NguoiDungDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();
    
    public List<NguoiDung> getNguoiDungByPage(int offset, int limit, int idPhanQuyen) {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from NguoiDung where idPhanQuyen = :idPhanQuyen";
            Query<NguoiDung> query = session.createQuery(hql, NguoiDung.class);
            query.setParameter("idPhanQuyen", idPhanQuyen);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            nguoiDungList = query.getResultList();
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
        return nguoiDungList;
    }
    
    public long getTotalNguoiDung(int idPhanQuyen) {
        long total = 0;
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "select count(*) from NguoiDung where idPhanQuyen = :idPhanQuyen";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("idPhanQuyen", idPhanQuyen);
            total = query.uniqueResult();

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
        return total;
    }
}
