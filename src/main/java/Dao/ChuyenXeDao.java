package Dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import FutaBus.bean.ChuyenXe;
import FutaBus.bean.ChuyenXeResult;
import FutaBus.bean.ChuyenXeUpdateDTO;
import FutaBus.bean.NguoiDung;
import FutaBus.bean.TuyenXe;
import FutaBus.bean.Xe;

@Repository
public class ChuyenXeDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();

    public List<ChuyenXe> getChuyenXeByPage(int offset, int limit) {
        List<ChuyenXe> chuyenXeList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "from ChuyenXe where trangThai != 0";
            Query<ChuyenXe> query = session.createQuery(hql, ChuyenXe.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            chuyenXeList = query.getResultList();
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
        return chuyenXeList;
    }

    public long getTotalChuyenXe() {
        long total = 0;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();

            String hql = "select count(*) from ChuyenXe where trangThai != 0";
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
    
    public List<ChuyenXeResult> getChuyenXe(int departureId, int destinationId, String departureDate, int tickets) {
        List<ChuyenXeResult> chuyenXeResultList = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();
            
            String hql = "SELECT " +
                    "    t.thoiGianDiChuyenTB, " +
                    "    bendi.tenBenXe AS tenBenXeDi, " +
                    "    benden.tenBenXe AS tenBenXeDen, " +
                    "    c.giaVe, " +
                    "    c.idChuyenXe, " +
                    "    c.thoiDiemDi, " +
                    "    c.thoiDiemDen, " +
                    "    lx.tenLoai, " +
                    "    COUNT(vt.idViTriGhe) AS soGheTrong, " +
                    "    x.idXe " +
                    "FROM " +
                    "    ChuyenXe c " +
                    "JOIN c.tuyenXe t " +
                    "JOIN t.benXeDi bendi " +
                    "JOIN t.benXeDen benden " +
                    "JOIN c.xe x " +
                    "JOIN x.loaiXe lx " +
                    "LEFT JOIN ViTriGhe vt ON vt.xe = x AND vt.trangThai = 0 " +
                    "WHERE " +
                    "    t.tinhThanhDi.id = :departureId AND " +
                    "    t.tinhThanhDen.id = :destinationId AND " +
                    "    CONVERT(DATE, c.thoiDiemDi) = CONVERT(DATE, :departureDate) " + 
                    "GROUP BY " +
                    "    t.thoiGianDiChuyenTB, bendi.tenBenXe, benden.tenBenXe, " +
                    "    c.giaVe, c.idChuyenXe, c.thoiDiemDi, c.thoiDiemDen, lx.tenLoai, x.idXe " +
                    "HAVING " +
                    "    COUNT(vt.idViTriGhe) >= :tickets";

            Query<Object[]> query = session.createQuery(hql);
            query.setParameter("departureId", departureId);
            query.setParameter("destinationId", destinationId);
            query.setParameter("departureDate", departureDate);
            query.setParameter("tickets", Long.valueOf(tickets));
            
            List<Object[]> resultList = query.getResultList();

            for (Object[] result : resultList) {
            	String thoiDiemDi = result[5] != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) result[5]) : null;
            	String thoiDiemDen = result[6] != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) result[6]) : null;
            	ChuyenXeResult chuyenXeResult = new ChuyenXeResult(
            	        (float) result[0],
            	        (String) result[1],
            	        (String) result[2],
            	        (Double) result[3],
            	        (int) result[4],
            	        (String) thoiDiemDi,
            	        (String) thoiDiemDen,
            	        (String) result[7],
            	        (Long) result[8],
            	        (int) result[9]
            	);
            	chuyenXeResultList.add(chuyenXeResult);
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
        return chuyenXeResultList;
    }
    
    public ChuyenXe getChuyenXeById(int idChuyenXe) {
        ChuyenXe chuyenXe = null;
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM ChuyenXe WHERE idChuyenXe = :idChuyenXe";
            Query<ChuyenXe> query = session.createQuery(hql, ChuyenXe.class);
            query.setParameter("idChuyenXe", idChuyenXe);

            chuyenXe = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return chuyenXe;
    }
    
    public boolean updateChuyenXe(ChuyenXe chuyenXe) {
        Session session = null;
        Transaction transaction = null;
        boolean isUpdated = false;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();
            transaction = session.beginTransaction();

            ChuyenXe existingChuyenXe = session.get(ChuyenXe.class, chuyenXe.getIdChuyenXe());

            if (existingChuyenXe != null) {
                existingChuyenXe.setThoiDiemDi(chuyenXe.getThoiDiemDi());
                existingChuyenXe.setThoiDiemDen(chuyenXe.getThoiDiemDen());
                existingChuyenXe.setGiaVe(chuyenXe.getGiaVe());
                existingChuyenXe.setTrangThai(chuyenXe.getTrangThai());

                existingChuyenXe.setXe(chuyenXe.getXe());
                existingChuyenXe.setTaiXe(chuyenXe.getTaiXe());
                existingChuyenXe.setTuyenXe(chuyenXe.getTuyenXe());

                session.update(existingChuyenXe);
                transaction.commit();
                isUpdated = true;
            } else {
                System.out.println("Chuyến xe không tồn tại");
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
    
    public boolean xoaChuyenXe(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            ChuyenXe chuyenXe = session.get(ChuyenXe.class, id);
            if (chuyenXe != null) {
            	chuyenXe.setTrangThai(0);
                session.update(chuyenXe);
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
    
    public ChuyenXe themChuyenXe(ChuyenXe chuyenXe) {
        Transaction transaction = null;
        Session session = null;
        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();
            transaction = session.beginTransaction();

            session.save(chuyenXe);

            transaction.commit();
            return chuyenXe;
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
    public List<ChuyenXe> getAllChuyenXe() {
        List<ChuyenXe> chuyenXeList = new ArrayList<>();
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }

            session = factory.openSession();

            String hql = "FROM ChuyenXe WHERE trangThai != 0";
            Query<ChuyenXe> query = session.createQuery(hql, ChuyenXe.class);
            chuyenXeList = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return chuyenXeList;
    }
}

