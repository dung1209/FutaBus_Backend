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

            String hql = "from ChuyenXe";
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

            String hql = "select count(*) from ChuyenXe";
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
                    "    t.giaHienHanh, " +
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
                    "    t.giaHienHanh, c.idChuyenXe, c.thoiDiemDi, c.thoiDiemDen, lx.tenLoai, x.idXe " +
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
    
}

