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

    @Column(name = "trangThai", nullable = false)
    private int trangThai;
    
    @Column(name = "thoiGianCapNhat", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianCapNhat;
    
    @Column(name = "hoTen", nullable = false, length = 255)
    private String hoTen;
    
    @Column(name = "soDienThoai", nullable = false, length = 10)
    private String soDienThoai;
    
    @Column(name = "email", nullable = false, length = 320)
    private String email;

    @Column(name = "idNguoiDung", nullable = false)
    private int idNguoiDung;
    
    @Column(name = "idChuyenXe", nullable = false)
    private int idChuyenXe;

    public PhieuDatVe() {
    }

	public PhieuDatVe(int idPhieuDatVe, Date thoiGianDatVe, int soLuongVe, BigDecimal tongTien, int trangThai,
			Date thoiGianCapNhat, String hoTen, String soDienThoai, String email, int idNguoiDung, int idChuyenXe) {
		super();
		this.idPhieuDatVe = idPhieuDatVe;
		this.thoiGianDatVe = thoiGianDatVe;
		this.soLuongVe = soLuongVe;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
		this.thoiGianCapNhat = thoiGianCapNhat;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.idNguoiDung = idNguoiDung;
		this.idChuyenXe = idChuyenXe;
	}

	public int getIdChuyenXe() {
		return idChuyenXe;
	}

	public void setIdChuyenXe(int idChuyenXe) {
		this.idChuyenXe = idChuyenXe;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTrangThai() {
		return trangThai;
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

    public int isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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
		return "PhieuDatVe [idPhieuDatVe=" + idPhieuDatVe + ", thoiGianDatVe=" + thoiGianDatVe + ", soLuongVe="
				+ soLuongVe + ", tongTien=" + tongTien + ", trangThai=" + trangThai + ", thoiGianCapNhat="
				+ thoiGianCapNhat + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", email=" + email
				+ ", idNguoiDung=" + idNguoiDung + ", idChuyenXe=" + idChuyenXe + "]";
	}

}
