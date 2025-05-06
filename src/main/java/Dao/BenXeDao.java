package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;
import java.util.ArrayList;
import FutaBus.bean.BenXe;

@Repository
public class BenXeDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();

    public List<BenXe> getAllBenXe() {
        List<BenXe> benXeList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM BenXe"; 
            Query<BenXe> query = session.createQuery(hql, BenXe.class);
            benXeList = query.getResultList();

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
        return benXeList;
    }
    
    public BenXe getBenXeById(int idBenXe) {
        BenXe benXe = null;
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM BenXe WHERE idBenXe = :idBenXe";
            Query<BenXe> query = session.createQuery(hql, BenXe.class);
            query.setParameter("idBenXe", idBenXe);

            benXe = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return benXe;
    }

}
