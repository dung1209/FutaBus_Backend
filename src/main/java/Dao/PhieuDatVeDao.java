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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
		List<Object[]> result = new ArrayList<>();
		List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
		Session session = null;

		try {
			session = HibernateUtils.getSessionFactory().openSession();

			String hql = "SELECT pdv, cx, tx " + "FROM PhieuDatVe pdv "
					+ "JOIN ChuyenXe cx ON pdv.idChuyenXe = cx.idChuyenXe "
					+ "JOIN TuyenXe tx ON cx.tuyenXe.idTuyenXe = tx.idTuyenXe "
					+ "WHERE pdv.idNguoiDung = :idNguoiDung";

			Query<Object[]> query = session.createQuery(hql);
			query.setParameter("idNguoiDung", idNguoiDung);
			result = query.getResultList();

			for (Object[] record : result) {
				PhieuDatVe pdv = (PhieuDatVe) record[0];
				ChuyenXe cx = (ChuyenXe) record[1];
				TuyenXe tx = (TuyenXe) record[2];

				PurchaseHistory purchaseHistory = new PurchaseHistory(pdv.getIdPhieuDatVe(), tx.getTenTuyen(), cx.getThoiDiemDi(), pdv.getSoLuongVe(), pdv.getTongTien(), pdv.getTrangThai());
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
			    System.out.println("---------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
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

}
