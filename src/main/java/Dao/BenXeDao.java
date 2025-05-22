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
import FutaBus.bean.BenXeDTO;

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

            String hql = "FROM BenXe where trangThai != 0"; 
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
    
    public long getTotalBenXe() {
        long total = 0;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();

            String hql = "select count(*) from BenXe where trangThai != 0";
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
    
    public List<BenXe> getBenXeByPage(int offset, int limit) {
        List<BenXe> benXeList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from BenXe where trangThai != 0";
            Query<BenXe> query = session.createQuery(hql, BenXe.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

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
    
    public boolean updateBenXe(BenXe benXe) {
        Session session = null;
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            BenXe existingBenXe = session.get(BenXe.class, benXe.getIdBenXe());

            if (existingBenXe != null) {
                existingBenXe.setTenBenXe(benXe.getTenBenXe());
                existingBenXe.setDiaChi(benXe.getDiaChi());
                existingBenXe.setSoDienThoai(benXe.getSoDienThoai());
                existingBenXe.setQuanHuyen(benXe.getQuanHuyen());

                session.update(existingBenXe);
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
    
    public boolean xoaBenXe(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            BenXe benXe = session.get(BenXe.class, id);
            if (benXe != null) {
                benXe.setTrangThai(0);
                session.update(benXe);
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
    
    public BenXe themBenXe(BenXe benXe) {
        Transaction transaction = null;
        Session session = null;
        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            session.save(benXe);

            transaction.commit();
            return benXe;
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
    
    public List<BenXeDTO> getAllBenXeDTO() {
        List<BenXeDTO> benXeDTOList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM BenXe WHERE trangThai != 0";
            Query<BenXe> query = session.createQuery(hql, BenXe.class);
            List<BenXe> benXeList = query.getResultList();

            for (BenXe benXe : benXeList) {
                int idQuanHuyen = benXe.getQuanHuyen() != null ? benXe.getQuanHuyen().getIdQuanHuyen() : 0;

                BenXeDTO dto = new BenXeDTO(
                    benXe.getIdBenXe(),
                    benXe.getTenBenXe(),
                    benXe.getDiaChi(),
                    benXe.getSoDienThoai(),
                    idQuanHuyen,
                    benXe.getTrangThai()
                );

                benXeDTOList.add(dto);
            }

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

        return benXeDTOList;
    }

}
