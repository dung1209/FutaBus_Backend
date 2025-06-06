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
import FutaBus.bean.QuanHuyen;
import FutaBus.bean.Xe;

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

            String hql = "from QuanHuyen where trangThai != 0";
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

            String hql = "select count(*) from QuanHuyen where trangThai != 0";
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
    
    public List<QuanHuyen> getAllQuanHuyen() {
        List<QuanHuyen> quanHuyenList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM QuanHuyen where trangThai != 0";
            Query<QuanHuyen> query = session.createQuery(hql, QuanHuyen.class);
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
    
    public QuanHuyen getQuanHuyenById(int idQuanHuyen) {
    	QuanHuyen quanHuyen = null;
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM QuanHuyen WHERE idQuanHuyen = :idQuanHuyen";
            Query<QuanHuyen> query = session.createQuery(hql, QuanHuyen.class);
            query.setParameter("idQuanHuyen", idQuanHuyen);

            quanHuyen = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return quanHuyen;
    }
    
    public boolean updateQuanHuyen(QuanHuyen quanHuyen) {
        Session session = null;
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            QuanHuyen existingQuanHuyen = session.get(QuanHuyen.class, quanHuyen.getIdQuanHuyen());

            if (existingQuanHuyen != null) {
                existingQuanHuyen.setTenQuanHuyen(quanHuyen.getTenQuanHuyen());

                if (quanHuyen.getTinhThanh() != null) {
                    existingQuanHuyen.setTinhThanh(quanHuyen.getTinhThanh());
                }

                session.update(existingQuanHuyen);
                transaction.commit();
                isUpdated = true;
            } else {
                System.out.println("Quận/Huyện không tồn tại");
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
    
    public boolean xoaQuanHuyen(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            QuanHuyen quanHuyen = session.get(QuanHuyen.class, id);
            if (quanHuyen != null) {
            	quanHuyen.setTrangThai(0);
                session.update(quanHuyen);
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
    
    public QuanHuyen themQuanHuyen(QuanHuyen quanHuyen) {
        Transaction transaction = null;
        Session session = null;
        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            session.save(quanHuyen);

            transaction.commit();
            return quanHuyen;
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
    public List<QuanHuyen> getAllQuanHuyenByTinh(int idTinhThanh) {
        List<QuanHuyen> quanHuyenList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM QuanHuyen where idTinhThanh = :idTinhThanh";
            Query<QuanHuyen> query = session.createQuery(hql, QuanHuyen.class);
            query.setParameter("idTinhThanh", idTinhThanh);
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

}
