package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;
import java.util.ArrayList;

import FutaBus.bean.ViTriGhe;

@Repository
public class ViTriGheDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();
    
    public List<ViTriGhe> getViTriGheTangDuoiByIdXe(int idXe) {
        List<ViTriGhe> viTriGheList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM ViTriGhe WHERE idXe = :idXe AND tenViTri LIKE 'A%'";
            Query<ViTriGhe> query = session.createQuery(hql, ViTriGhe.class);
            query.setParameter("idXe", idXe);

            viTriGheList = query.getResultList();
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
        return viTriGheList;
    }
    
    public List<ViTriGhe> getViTriGheTangTrenByIdXe(int idXe) {
        List<ViTriGhe> viTriGheList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM ViTriGhe WHERE idXe = :idXe AND tenViTri LIKE 'B%'";
            Query<ViTriGhe> query = session.createQuery(hql, ViTriGhe.class);
            query.setParameter("idXe", idXe);

            viTriGheList = query.getResultList();
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
        return viTriGheList;
    }
    
    public void updateTrangThai(int gheId, int trangThai) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "UPDATE ViTriGhe SET trangThai = :trangThai WHERE id = :gheId";
            Query<?> query = session.createQuery(hql);
            query.setParameter("trangThai", trangThai);
            query.setParameter("gheId", gheId);

            query.executeUpdate();
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
    }

}