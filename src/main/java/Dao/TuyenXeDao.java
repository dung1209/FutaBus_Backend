package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;
import java.util.ArrayList;
import FutaBus.bean.TuyenXe;

@Repository
public class TuyenXeDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();

    public List<TuyenXe> getTuyenXeByPage(int offset, int limit) {
        List<TuyenXe> tuyenXeList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from TuyenXe where trangThai != 0";
            Query<TuyenXe> query = session.createQuery(hql, TuyenXe.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            tuyenXeList = query.getResultList();
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
        return tuyenXeList;
    }

    public long getTotalTuyenXe() {
        long total = 0;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();

            String hql = "select count(*) from TuyenXe where trangThai != 0";
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
    
    public TuyenXe getTuyenXeById(int idTuyenXe) {
        TuyenXe tuyenXe = null;
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM TuyenXe WHERE idTuyenXe = :idTuyenXe";
            Query<TuyenXe> query = session.createQuery(hql, TuyenXe.class);
            query.setParameter("idTuyenXe", idTuyenXe);

            tuyenXe = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return tuyenXe;
    }
    
    public boolean updateTuyenXe(TuyenXe tuyenXe) {
        Session session = null;
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            TuyenXe existingTuyenXe = session.get(TuyenXe.class, tuyenXe.getIdTuyenXe());

            if (existingTuyenXe != null) {
                existingTuyenXe.setTenTuyen(tuyenXe.getTenTuyen());
                existingTuyenXe.setBenXeDi(tuyenXe.getBenXeDi());
                existingTuyenXe.setBenXeDen(tuyenXe.getBenXeDen());
                existingTuyenXe.setTinhThanhDi(tuyenXe.getTinhThanhDi());
                existingTuyenXe.setTinhThanhDen(tuyenXe.getTinhThanhDen());
                existingTuyenXe.setQuangDuong(tuyenXe.getQuangDuong());
                existingTuyenXe.setThoiGianDiChuyenTB(tuyenXe.getThoiGianDiChuyenTB());
                existingTuyenXe.setSoChuyenTrongNgay(tuyenXe.getSoChuyenTrongNgay());
                existingTuyenXe.setSoNgayChayTrongTuan(tuyenXe.getSoNgayChayTrongTuan());

                session.update(existingTuyenXe);
                transaction.commit();
                isUpdated = true;
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
    
    public boolean xoaTuyenXe(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            TuyenXe tuyenXe = session.get(TuyenXe.class, id);
            if (tuyenXe != null) {
                tuyenXe.setTrangThai(0);
                session.update(tuyenXe);
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
