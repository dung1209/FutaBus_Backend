package FutaBus.bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PhieuDatVe")
public class PhieuDatVe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPhieuDatVe")
    private int idPhieuDatVe;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thoiGianDatVe", nullable = false)
    private Date thoiGianDatVe;

    @Column(name = "soLuongVe", nullable = false)
    private int soLuongVe;

    @Column(name = "tongTien", nullable = false, precision = 10, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "trangThaiVe", nullable = false)
    private int trangThaiVe;
    
    @Column(name = "thoiGianCapNhat", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianCapNhat;

    @Column(name = "idNguoiDung", nullable = false)
    private int idNguoiDung;

    public PhieuDatVe() {
    }

    public PhieuDatVe(int idPhieuDatVe, Date thoiGianDatVe, int soLuongVe, BigDecimal tongTien, int trangThaiVe, Date thoiGianCapNhat, int idNguoiDung) {
        this.idPhieuDatVe = idPhieuDatVe;
        this.thoiGianDatVe = thoiGianDatVe;
        this.soLuongVe = soLuongVe;
        this.tongTien = tongTien;
        this.trangThaiVe = trangThaiVe;
        this.thoiGianCapNhat = thoiGianCapNhat;
        this.idNguoiDung = idNguoiDung;
    }

    public int getIdPhieuDatVe() {
        return idPhieuDatVe;
    }

    public void setIdPhieuDatVe(int idPhieuDatVe) {
        this.idPhieuDatVe = idPhieuDatVe;
    }

    public Date getThoiGianDatVe() {
        return thoiGianDatVe;
    }

    public void setThoiGianDatVe(Date thoiGianDatVe) {
        this.thoiGianDatVe = thoiGianDatVe;
    }

    public int getSoLuongVe() {
        return soLuongVe;
    }

    public void setSoLuongVe(int soLuongVe) {
        this.soLuongVe = soLuongVe;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public int isTrangThaiVe() {
        return trangThaiVe;
    }

    public void setTrangThaiVe(int trangThaiVe) {
        this.trangThaiVe = trangThaiVe;
    }

    public Date getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(Date thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    @Override
    public String toString() {
        return "PhieuDatVe{" +
                "idPhieuDatVe=" + idPhieuDatVe +
                ", thoiGianDatVe=" + thoiGianDatVe +
                ", soLuongVe=" + soLuongVe +
                ", tongTien=" + tongTien +
                ", trangThaiVe=" + trangThaiVe +
                ", thoiGianCapNhat=" + thoiGianCapNhat +
                ", idNguoiDung=" + idNguoiDung +
                '}';
    }
}
