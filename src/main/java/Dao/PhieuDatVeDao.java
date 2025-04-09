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
import FutaBus.bean.PhieuDatVe;
import FutaBus.bean.QuanHuyen;
import FutaBus.bean.VeXe;

@Repository
public class PhieuDatVeDao {
    
    private static SessionFactory factory = HibernateUtils.getSessionFactory();
    
    public void saveBooking(BookingRequest bookingRequest) {
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
            phieu.setIdNguoiDung(1);
            phieu.setIdChuyenXe(bookingRequest.getIdChuyenXe());
            phieu.setTrangThai(1);

            PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
            int idPhieuDatVe = save(session, phieu);

            List<Integer> danhSachGhe = Arrays.stream(bookingRequest.getIdViTriGhe().split(","))
                                              .map(Integer::parseInt)
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
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
 	public int save(Session session, PhieuDatVe phieu) {
      	return (int) session.save(phieu);
  	}

}
