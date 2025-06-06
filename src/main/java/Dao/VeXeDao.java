package Dao;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import FutaBus.bean.PurchaseHistory;
import FutaBus.bean.PurchaseItemResponse;
import FutaBus.bean.VeXe;
import HibernateUtils.HibernateUtils;

@Repository
public class VeXeDao {
	
	public void save(Session session, VeXe veXe) {
        session.save(veXe);
    }
	public PurchaseItemResponse getLichSuMuaVeByIdPhieuDatVe(int idPhieudatve) {
	    Session session = null;
	    PurchaseItemResponse purchaseItemResponse = null;

	    try {
	        session = HibernateUtils.getSessionFactory().openSession();

	        String sql =
	                "SELECT pdv.idPhieuDatVe, tx.tenTuyen, cx.thoiDiemDi, pdv.soLuongVe, pdv.tongTien, pdv.trangThai, " +
	                "    STRING_AGG(CAST(vtg.tenViTri AS VARCHAR), ', ') AS danhSachGhe, nd.hoTen AS hoTenNguoiDatVe, " +
	                "    nd.soDienThoai AS SDTNguoiDatVe, nd.email AS EmailNguoiDatVe, pdv.thoiGianDatVe, " +
	                "    xe.bienSo, " +
	                "    lx.tenLoai AS loaiXe, " +
	                "    bxdi.diaChi AS diaChiBenXeDi, " +
	                "    bxdi.soDienThoai AS SDTBenXeDi, " +
	                "    bxden.diaChi AS diaChiBenXeDen " +
	                "FROM PhieuDatVe pdv " +
	                "JOIN ChuyenXe cx ON pdv.idChuyenXe = cx.idChuyenXe " +
	                "JOIN TuyenXe tx ON cx.idTuyenXe = tx.idTuyenXe " +
	                "JOIN VeXe vx ON vx.idPhieuDatVe = pdv.idPhieuDatVe " +
	                "JOIN ViTriGhe vtg ON vx.idViTriGhe = vtg.idViTriGhe " +
	                "JOIN NguoiDung nd ON pdv.idNguoiDung = nd.idNguoiDung " +
	                "JOIN Xe xe ON cx.idXe = xe.idXe " +
	                "JOIN LoaiXe lx ON xe.idLoaiXe = lx.idLoaiXe " +
	                "JOIN BenXe bxdi ON tx.idBenXeDi = bxdi.idBenXe " +
	                "JOIN BenXe bxden ON tx.idBenXeDen = bxden.idBenXe " +
	                "WHERE pdv.idPhieuDatVe = :idPhieudatve " +
	                "GROUP BY pdv.idPhieuDatVe, tx.tenTuyen, cx.thoiDiemDi, pdv.soLuongVe, pdv.tongTien, " +
	                "    pdv.trangThai, nd.hoTen, nd.soDienThoai, nd.email, pdv.thoiGianDatVe, " +
	                "    xe.bienSo, lx.tenLoai, bxdi.diaChi, bxdi.soDienThoai, bxden.diaChi";

	        Query<Object[]> query = session.createSQLQuery(sql);
	        query.setParameter("idPhieudatve", idPhieudatve);

	        // Lấy duy nhất một kết quả
	        Object[] row = (Object[]) query.getSingleResult();

	        purchaseItemResponse = new PurchaseItemResponse();
	        purchaseItemResponse.setIdPhieuDatVe((Integer) row[0]);
	        purchaseItemResponse.setTenTuyen((String) row[1]);
	        purchaseItemResponse.setThoiDiemDi((Timestamp) row[2]);
	        purchaseItemResponse.setSoLuongVe((Integer) row[3]);
	        purchaseItemResponse.setTongTien((BigDecimal) row[4]);
	        purchaseItemResponse.setTrangThai(((Number) row[5]).intValue());
	        purchaseItemResponse.setDanhSachIDGhe((String) row[6]);
	        purchaseItemResponse.setHoTenNguoiDatVe((String) row[7]);
	        purchaseItemResponse.setSDTNguoiDatVe((String) row[8]);
	        purchaseItemResponse.setEmailNguoiDatVe((String) row[9]);
	        purchaseItemResponse.setThoiGianDatVe((Timestamp) row[10]);
	        purchaseItemResponse.setBienSoXe((String) row[11]);
	        purchaseItemResponse.setLoaiXe((String) row[12]);
	        purchaseItemResponse.setDiaChiBenXeDi((String) row[13]);
	        purchaseItemResponse.setSDTBenXeDi((String) row[14]);
	        purchaseItemResponse.setDiaChiBenXeDen((String) row[15]);

	        // Debug
	        System.out.println("---------------------------------------------");
	        System.out.println("ID Phieu Dat Ve: " + purchaseItemResponse.getIdPhieuDatVe());
	        System.out.println("Ten Tuyen: " + purchaseItemResponse.getTenTuyen());
	        System.out.println("Thoi Diem Di: " + purchaseItemResponse.getThoiDiemDi());
	        System.out.println("So Luong Ve: " + purchaseItemResponse.getSoLuongVe());
	        System.out.println("Tong Tien: " + purchaseItemResponse.getTongTien());
	        System.out.println("Trang Thai: " + purchaseItemResponse.getTrangThai());
	        System.out.println("Danh Sach Ghe: " + purchaseItemResponse.getDanhSachIDGhe());
	        System.out.println("Ho Ten Nguoi Dat: " + purchaseItemResponse.getHoTenNguoiDatVe());
	        System.out.println("---------------------------------------------");

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return purchaseItemResponse;
	}
}