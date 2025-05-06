package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;
import java.util.ArrayList;

import FutaBus.bean.ChuyenXe;
import FutaBus.bean.ViTriGhe;
import FutaBus.bean.Xe;

@Repository
public class XeDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();

    public List<Xe> getXeByPage(int offset, int limit) {
        List<Xe> xeList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from Xe where trangThai != 0 ";
            Query<Xe> query = session.createQuery(hql, Xe.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            xeList = query.getResultList();
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
        return xeList;
    }

    public long getTotalXe() {
        long total = 0;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();

            String hql = "select count(*) from Xe where trangThai != 0 ";
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
    
    public List<Xe> getAllXe() {
        List<Xe> xeList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from Xe";
            Query<Xe> query = session.createQuery(hql, Xe.class);

            xeList = query.getResultList();
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
        return xeList;
    }
    
    public Xe getXeById(int idXe) {
        Xe xe = null;
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM Xe WHERE idXe = :idXe";
            Query<Xe> query = session.createQuery(hql, Xe.class);
            query.setParameter("idXe", idXe);

            xe = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return xe;
    }
    
    public boolean updateXe(Xe xe) {
        Session session = null;
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            Xe existingXe = session.get(Xe.class, xe.getIdXe());

            if (existingXe != null) {
                existingXe.setTenXe(xe.getTenXe());
                existingXe.setBienSo(xe.getBienSo());
                existingXe.setLoaiXe(xe.getLoaiXe());

                session.update(existingXe);
                transaction.commit();
                isUpdated = true;
            } else {
                System.out.println("Xe không tồn tại");
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
    
    public boolean xoaXe(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            Xe xe = session.get(Xe.class, id);
            if (xe != null) {
            	xe.setTrangThai(0);
                session.update(xe);
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
    
    public Xe themXe(Xe xe) {
        Transaction transaction = null;
        Session session = null;
        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            session.save(xe);

            transaction.commit();
            return xe;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public void themDanhSachViTriGheTheoXe(Xe xe, int soGhe) {
        Transaction transaction = null;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            int gheMoiBen = soGhe / 2;

            for (int i = 1; i <= gheMoiBen; i++) {
                ViTriGhe vtA = new ViTriGhe();
                vtA.setTenViTri("A" + i);
                vtA.setTrangThai(0);
                vtA.setXe(xe);
                session.save(vtA);

                ViTriGhe vtB = new ViTriGhe();
                vtB.setTenViTri("B" + i);
                vtB.setTrangThai(0);
                vtB.setXe(xe);
                session.save(vtB);
            }
            
            session.flush();
            session.clear(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
    
}
