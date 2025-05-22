package Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import HibernateUtils.HibernateUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import FutaBus.bean.BookingInfo;
import FutaBus.bean.BookingRequest;
import FutaBus.bean.ChuyenXe;
import FutaBus.bean.PhieuDatVe;
import FutaBus.bean.PurchaseHistory;
import FutaBus.bean.QuanHuyen;
import FutaBus.bean.TuyenXe;
import FutaBus.bean.VeXe;

@Repository
public class PhieuDatVeDao {

	private static SessionFactory factory = HibernateUtils.getSessionFactory();

	public void saveBooking(BookingRequest bookingRequest, int idNguoiDung) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			PhieuDatVe phieu = new PhieuDatVe();
			phieu.setThoiGianDatVe(new Date());
			phieu.setThoiGianCapNhat(new Date());
			phieu.setSoLuongVe(bookingRequest.getSoLuongVe());
			phieu.setTongTien(BigDecimal.valueOf(bookingRequest.getTongTien()));
			phieu.setHoTen(bookingRequest.getHoTen());
			phieu.setSoDienThoai(bookingRequest.getSoDienThoai());
			phieu.setEmail(bookingRequest.getEmail());
			phieu.setIdNguoiDung(idNguoiDung);
			phieu.setIdChuyenXe(bookingRequest.getIdChuyenXe());
			phieu.setTrangThai(1);

			PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
			int idPhieuDatVe = save(session, phieu);

			List<Integer> danhSachGhe = Arrays.stream(bookingRequest.getIdViTriGhe().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			VeXeDao veXeDao = new VeXeDao();
			ViTriGheDao viTriGheDao = new ViTriGheDao();
			for (Integer gheId : danhSachGhe) {
				VeXe ve = new VeXe();
				ve.setIdPhieuDatVe(idPhieuDatVe);
				ve.setIdViTriGhe(gheId);
				ve.setTrangThai(1);
				veXeDao.save(session, ve);
				viTriGheDao.updateTrangThai(gheId, 1);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public int save(Session session, PhieuDatVe phieu) {
		return (int) session.save(phieu);
	}
	
	public List<PurchaseHistory> getLichSuMuaVeByIdNguoiDung(int idNguoiDung) {
	    List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
	    Session session = null;

	    try {
	        session = HibernateUtils.getSessionFactory().openSession();

	        String sql = "SELECT pdv.idPhieuDatVe, tx.tenTuyen, cx.thoiDiemDi, pdv.soLuongVe, pdv.tongTien, pdv.trangThai, " +
	                     "STRING_AGG(CAST(vtg.idViTriGhe AS VARCHAR), ', ') AS danhSachIDGhe " +
	                     "FROM PhieuDatVe pdv " +
	                     "JOIN ChuyenXe cx ON pdv.idChuyenXe = cx.idChuyenXe " +
	                     "JOIN TuyenXe tx ON cx.idTuyenXe = tx.idTuyenXe " +
	                     "JOIN VeXe vx ON vx.idPhieuDatVe = pdv.idPhieuDatVe " +
	                     "JOIN ViTriGhe vtg ON vx.idViTriGhe = vtg.idViTriGhe " +
	                     "WHERE pdv.idNguoiDung = :idNguoiDung " +
	                     "GROUP BY pdv.idPhieuDatVe, tx.tenTuyen, cx.thoiDiemDi, pdv.soLuongVe, pdv.tongTien, pdv.trangThai";

	        Query<Object[]> query = session.createSQLQuery(sql);
	        query.setParameter("idNguoiDung", idNguoiDung);
	        List<Object[]> result = query.getResultList();

	        for (Object[] record : result) {
	            int idPhieuDatVe = (Integer) record[0];
	            String tenTuyen = (String) record[1];
	            Date thoiDiemDi = (Timestamp) record[2];
	            int soLuongVe = (Integer) record[3];
	            BigDecimal tongTien = (BigDecimal) record[4];
	            int trangThai = ((Number) record[5]).intValue();
	            String danhSachIDGhe = (String) record[6];

	            PurchaseHistory purchaseHistory = new PurchaseHistory(
	                idPhieuDatVe,
	                tenTuyen,
	                thoiDiemDi,
	                soLuongVe,
	                tongTien,
	                trangThai,
	                danhSachIDGhe
	            );

	            purchaseHistoryList.add(purchaseHistory);
	        }

	        for (PurchaseHistory purchaseHistory : purchaseHistoryList) {
	            System.out.println("---------------------------------------------");
	            System.out.println("ID Phieu Dat Ve: " + purchaseHistory.getIdPhieuDatVe());
	            System.out.println("Ten Tuyen: " + purchaseHistory.getTenTuyen());
	            System.out.println("Thoi Diu Di: " + purchaseHistory.getThoiDiemDi());
	            System.out.println("So Luong Ve: " + purchaseHistory.getSoLuongVe());
	            System.out.println("Tong Tien: " + purchaseHistory.getTongTien());
	            System.out.println("Trang Thai: " + purchaseHistory.getTrangThai());
	            System.out.println("danhSachIDGhe: " + purchaseHistory.getDanhSachIDGhe());
	            System.out.println("---------------------------------------------");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return purchaseHistoryList;
	}
	
	public BigDecimal getTongDoanhThuThangHienTai() {
	    Session session = HibernateUtils.getSessionFactory().openSession();
	    BigDecimal tongDoanhThu = BigDecimal.ZERO;

	    try {
	        String hql = "SELECT COALESCE(SUM(p.tongTien), 0) " +
	                     "FROM PhieuDatVe p " +
	                     "WHERE MONTH(p.thoiGianDatVe) = MONTH(CURRENT_DATE()) " +
	                     "AND YEAR(p.thoiGianDatVe) = YEAR(CURRENT_DATE()) " +
	                     "AND p.trangThai = 4";

	        tongDoanhThu = (BigDecimal) session.createQuery(hql).uniqueResult();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return tongDoanhThu;
	}
	
	public long getTotalBookingInfo() {
        long total = 0;
        Session session = null;

        try {
            if (factory == null) {
                factory = HibernateUtils.getSessionFactory();
            }
            session = factory.openSession();

            String hql = "select count(*) from PhieuDatVe";
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
	
	public List<BookingInfo> getBookingInfoByPage(int offset, int limit) {
	    List<BookingInfo> list = new ArrayList<>();
	    Session session = null;

	    try {
	        session = HibernateUtils.getSessionFactory().openSession();

	        String sql = "SELECT " +
	                "pdv.idPhieuDatVe, pdv.hoTen, pdv.soDienThoai, pdv.email, " +
	                "cx.thoiDiemDi, cx.thoiDiemDen, cx.giaVe, pdv.soLuongVe, " +
	                "pdv.tongTien, pdv.trangThai, pdv.thoiGianDatVe, " +
	                "tx.tenTuyen, bxDi.tenBenXe AS benDi, bxDen.tenBenXe AS benDen, " +
	                "x.bienSo AS bienSoXe, lx.tenLoai AS loaiXe, " +
	                "STRING_AGG(vtg.tenViTri, ', ') AS danhSachGhe, " +
	                "STRING_AGG(CAST(vtg.idViTriGhe AS VARCHAR), ', ') AS danhSachIDGhe " +
	                "FROM PhieuDatVe pdv " +
	                "JOIN NguoiDung nd ON pdv.idNguoiDung = nd.idNguoiDung " +
	                "JOIN ChuyenXe cx ON pdv.idChuyenXe = cx.idChuyenXe " +
	                "JOIN TuyenXe tx ON cx.idTuyenXe = tx.idTuyenXe " +
	                "JOIN BenXe bxDi ON tx.idBenXeDi = bxDi.idBenXe " +
	                "JOIN BenXe bxDen ON tx.idBenXeDen = bxDen.idBenXe " +
	                "JOIN Xe x ON cx.idXe = x.idXe " +
	                "JOIN LoaiXe lx ON x.idLoaiXe = lx.idLoaiXe " +
	                "JOIN VeXe vx ON vx.idPhieuDatVe = pdv.idPhieuDatVe " +
	                "JOIN ViTriGhe vtg ON vx.idViTriGhe = vtg.idViTriGhe " +
	                "GROUP BY pdv.idPhieuDatVe, pdv.hoTen, pdv.soDienThoai, pdv.email, " +
	                "cx.thoiDiemDi, cx.thoiDiemDen, cx.giaVe, pdv.soLuongVe, pdv.tongTien, " +
	                "pdv.trangThai, pdv.thoiGianDatVe, tx.tenTuyen, bxDi.tenBenXe, bxDen.tenBenXe, " +
	                "x.bienSo, lx.tenLoai " +
	                "ORDER BY pdv.thoiGianDatVe DESC " +
	                "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";

	        @SuppressWarnings("unchecked")
	        List<Object[]> rows = session.createNativeQuery(sql)
	                .setParameter("offset", offset)
	                .setParameter("limit", limit)
	                .getResultList();

	        for (Object[] row : rows) {
	            BookingInfo info = new BookingInfo();

	            info.setIdPhieuDatVe((int) row[0]);
	            info.setHoTen((String) row[1]);
	            info.setSoDienThoai((String) row[2]);
	            info.setEmail((String) row[3]);
	            info.setThoiDiemDi(row[4].toString());
	            info.setThoiDiemDen(row[5].toString());
	            info.setGiaVe(((Number) row[6]).doubleValue());
	            info.setSoLuongVe((int) row[7]);
	            info.setTongTien(((Number) row[8]).doubleValue());
	            info.setTrangThai(row[9].toString());
	            info.setThoiGianDatVe(row[10].toString());
	            info.setTenTuyen((String) row[11]);
	            info.setBenDi((String) row[12]);
	            info.setBenDen((String) row[13]);
	            info.setBienSoXe((String) row[14]);
	            info.setLoaiXe((String) row[15]);
	            info.setDanhSachGhe((String) row[16]);
	            info.setDanhSachIDGhe((String) row[17]);

	            list.add(info);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null)
	            session.close();
	    }
	    return list;
	}
	
	public List<Object[]> getDoanhThuTheoNgay(String startDate, String endDate) {
	    List<Object[]> result = new ArrayList<>();
	    Session session = null;

	    try {
	        session = HibernateUtils.getSessionFactory().openSession();

	        String hql = "SELECT p.thoiGianDatVe, SUM(p.tongTien) " +
	                     "FROM PhieuDatVe p " +
	                     "WHERE p.trangThai IN (3, 4) " +
	                     "AND p.thoiGianDatVe >= :start " +
	                     "AND p.thoiGianDatVe < :end " +
	                     "GROUP BY p.thoiGianDatVe " +
	                     "ORDER BY p.thoiGianDatVe";

	        Timestamp startTimestamp = Timestamp.valueOf(startDate + " 00:00:00");
	        Timestamp endTimestamp = Timestamp.valueOf(endDate + " 23:59:59");

	        Query<Object[]> query = session.createQuery(hql, Object[].class);
	        query.setParameter("start", startTimestamp);
	        query.setParameter("end", endTimestamp);

	        result = query.getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return result;
	}
	
	public PhieuDatVe getPhieuDatVeById(int idPhieuDatVe) {
	    PhieuDatVe phieuDatVe = null;
	    Session session = null;
	    Transaction transaction = null;

	    try {
	        if (factory == null) {
	            factory = HibernateUtils.getSessionFactory();
	        }

	        session = factory.openSession();
	        transaction = session.beginTransaction();

	        String hql = "FROM PhieuDatVe WHERE idPhieuDatVe = :idPhieuDatVe";
	        Query<PhieuDatVe> query = session.createQuery(hql, PhieuDatVe.class);
	        query.setParameter("idPhieuDatVe", idPhieuDatVe);

	        phieuDatVe = query.uniqueResult();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session != null) session.close();
	    }

	    return phieuDatVe;
	}
	
	public boolean updatePhieuDatVe(int idPhieuDatVe, int trangThai) {
	    Session session = null;
	    Transaction transaction = null;

	    try {
	        if (factory == null) {
	            factory = HibernateUtils.getSessionFactory();
	        }
	        session = factory.openSession();
	        transaction = session.beginTransaction();

	        String hql = "UPDATE PhieuDatVe SET trangThai = :trangThai WHERE idPhieuDatVe = :idPhieuDatVe";
	        Query<?> query = session.createQuery(hql);
	        query.setParameter("trangThai", trangThai);
	        query.setParameter("idPhieuDatVe", idPhieuDatVe);

	        int rowsAffected = query.executeUpdate();
	        transaction.commit();

	        return rowsAffected > 0;
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
	
	public List<BookingInfo> getAllPhieuDatVe() {
	    List<BookingInfo> list = new ArrayList<>();
	    Session session = null;

	    try {
	        session = HibernateUtils.getSessionFactory().openSession();

	        String sql = "SELECT " +
	                "pdv.idPhieuDatVe, pdv.hoTen, pdv.soDienThoai, pdv.email, " +
	                "cx.thoiDiemDi, cx.thoiDiemDen, cx.giaVe, pdv.soLuongVe, " +
	                "pdv.tongTien, pdv.trangThai, pdv.thoiGianDatVe, " +
	                "tx.tenTuyen, bxDi.tenBenXe AS benDi, bxDen.tenBenXe AS benDen, " +
	                "x.bienSo AS bienSoXe, lx.tenLoai AS loaiXe, " +
	                "STRING_AGG(vtg.tenViTri, ', ') AS danhSachGhe, " +
	                "STRING_AGG(CAST(vtg.idViTriGhe AS VARCHAR), ', ') AS danhSachIDGhe " +
	                "FROM PhieuDatVe pdv " +
	                "JOIN NguoiDung nd ON pdv.idNguoiDung = nd.idNguoiDung " +
	                "JOIN ChuyenXe cx ON pdv.idChuyenXe = cx.idChuyenXe " +
	                "JOIN TuyenXe tx ON cx.idTuyenXe = tx.idTuyenXe " +
	                "JOIN BenXe bxDi ON tx.idBenXeDi = bxDi.idBenXe " +
	                "JOIN BenXe bxDen ON tx.idBenXeDen = bxDen.idBenXe " +
	                "JOIN Xe x ON cx.idXe = x.idXe " +
	                "JOIN LoaiXe lx ON x.idLoaiXe = lx.idLoaiXe " +
	                "JOIN VeXe vx ON vx.idPhieuDatVe = pdv.idPhieuDatVe " +
	                "JOIN ViTriGhe vtg ON vx.idViTriGhe = vtg.idViTriGhe " +
	                "GROUP BY pdv.idPhieuDatVe, pdv.hoTen, pdv.soDienThoai, pdv.email, " +
	                "cx.thoiDiemDi, cx.thoiDiemDen, cx.giaVe, pdv.soLuongVe, pdv.tongTien, " +
	                "pdv.trangThai, pdv.thoiGianDatVe, tx.tenTuyen, bxDi.tenBenXe, bxDen.tenBenXe, " +
	                "x.bienSo, lx.tenLoai " +
	                "ORDER BY pdv.thoiGianDatVe DESC";

	        @SuppressWarnings("unchecked")
	        List<Object[]> rows = session.createNativeQuery(sql).getResultList();

	        for (Object[] row : rows) {
	            BookingInfo info = new BookingInfo();

	            info.setIdPhieuDatVe((int) row[0]);
	            info.setHoTen((String) row[1]);
	            info.setSoDienThoai((String) row[2]);
	            info.setEmail((String) row[3]);
	            info.setThoiDiemDi(row[4].toString());
	            info.setThoiDiemDen(row[5].toString());
	            info.setGiaVe(((Number) row[6]).doubleValue());
	            info.setSoLuongVe((int) row[7]);
	            info.setTongTien(((Number) row[8]).doubleValue());
	            info.setTrangThai(row[9].toString());
	            info.setThoiGianDatVe(row[10].toString());
	            info.setTenTuyen((String) row[11]);
	            info.setBenDi((String) row[12]);
	            info.setBenDen((String) row[13]);
	            info.setBienSoXe((String) row[14]);
	            info.setLoaiXe((String) row[15]);
	            info.setDanhSachGhe((String) row[16]);
	            info.setDanhSachIDGhe((String) row[17]);

	            list.add(info);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null)
	            session.close();
	    }

	    return list;
	}

}
