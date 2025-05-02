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
import FutaBus.bean.QuanHuyen;
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

            String hql = "from TinhThanh where trangThai != 0";
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

            String hql = "select count(*) from TinhThanh where trangThai != 0";
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
    
    public TinhThanh getTinhThanhById(int idTinhThanh) {
    	TinhThanh tinhThanh = null;
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM TinhThanh WHERE idTinhThanh = :idTinhThanh";
            Query<TinhThanh> query = session.createQuery(hql, TinhThanh.class);
            query.setParameter("idTinhThanh", idTinhThanh);

            tinhThanh = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return tinhThanh;
    }
    
    public boolean updateTinhThanh(TinhThanh tinhThanh) {
        Session session = null;
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            TinhThanh existingTinh = session.get(TinhThanh.class, tinhThanh.getIdTinhThanh());

            if (existingTinh != null) {
                existingTinh.setTenTinh(tinhThanh.getTenTinh().trim());

                session.update(existingTinh);
                transaction.commit();
                isUpdated = true;
            } else {
                System.out.println("Tỉnh/Thành không tồn tại.");
            }
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

        return isUpdated;
    }
    
    public boolean xoaTinhThanh(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            TinhThanh tinhThanh = session.get(TinhThanh.class, id);
            if (tinhThanh != null) {
            	tinhThanh.setTrangThai(0);
                session.update(tinhThanh);
                transaction.commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
