package FutaBus.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class PurchaseItemResponse{
	private int idPhieuDatVe;
	private String tenTuyen;
	private Date thoiDiemDi;	
    private int soLuongVe;
    private BigDecimal tongTien;
    private int trangThai;
    private String danhSachIDGhe;
    private String hoTenNguoiDatVe;
    private String SDTNguoiDatVe;
    private String EmailNguoiDatVe;
    private Date thoiGianDatVe;
    private String bienSoXe;
    private String loaiXe;
    private String diaChiBenXeDi;
    private String SDTBenXeDi;
    private String diaChiBenXeDen;
	public int getIdPhieuDatVe() {
		return idPhieuDatVe;
	}
	public void setIdPhieuDatVe(int idPhieuDatVe) {
		this.idPhieuDatVe = idPhieuDatVe;
	}
	public String getTenTuyen() {
		return tenTuyen;
	}
	public void setTenTuyen(String tenTuyen) {
		this.tenTuyen = tenTuyen;
	}
	public Date getThoiDiemDi() {
		return thoiDiemDi;
	}
	public void setThoiDiemDi(Date thoiDiemDi) {
		this.thoiDiemDi = thoiDiemDi;
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
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public String getDanhSachIDGhe() {
		return danhSachIDGhe;
	}
	public void setDanhSachIDGhe(String danhSachIDGhe) {
		this.danhSachIDGhe = danhSachIDGhe;
	}
	public String getHoTenNguoiDatVe() {
		return hoTenNguoiDatVe;
	}
	public void setHoTenNguoiDatVe(String hoTenNguoiDatVe) {
		this.hoTenNguoiDatVe = hoTenNguoiDatVe;
	}
	public String getSDTNguoiDatVe() {
		return SDTNguoiDatVe;
	}
	public void setSDTNguoiDatVe(String sDTNguoiDatVe) {
		SDTNguoiDatVe = sDTNguoiDatVe;
	}
	public String getEmailNguoiDatVe() {
		return EmailNguoiDatVe;
	}
	public void setEmailNguoiDatVe(String emailNguoiDatVe) {
		EmailNguoiDatVe = emailNguoiDatVe;
	}
	public Date getThoiGianDatVe() {
		return thoiGianDatVe;
	}
	public void setThoiGianDatVe(Date thoiGianDatVe) {
		this.thoiGianDatVe = thoiGianDatVe;
	}
	public String getBienSoXe() {
		return bienSoXe;
	}
	public void setBienSoXe(String bienSoXe) {
		this.bienSoXe = bienSoXe;
	}
	public String getLoaiXe() {
		return loaiXe;
	}
	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}
	public String getDiaChiBenXeDi() {
		return diaChiBenXeDi;
	}
	public void setDiaChiBenXeDi(String diaChiBenXeDi) {
		this.diaChiBenXeDi = diaChiBenXeDi;
	}
	public String getSDTBenXeDi() {
		return SDTBenXeDi;
	}
	public void setSDTBenXeDi(String sDTBenXeDi) {
		SDTBenXeDi = sDTBenXeDi;
	}
	public String getDiaChiBenXeDen() {
		return diaChiBenXeDen;
	}
	public void setDiaChiBenXeDen(String diaChiBenXeDen) {
		this.diaChiBenXeDen = diaChiBenXeDen;
	}
	public PurchaseItemResponse(int idPhieuDatVe, String tenTuyen, Date thoiDiemDi, int soLuongVe, BigDecimal tongTien,
			int trangThai, String danhSachIDGhe, String hoTenNguoiDatVe, String sDTNguoiDatVe, String emailNguoiDatVe,
			Date thoiGianDatVe, String bienSoXe, String loaiXe, String diaChiBenXeDi, String sDTBenXeDi,
			String diaChiBenXeDen) {
		super();
		this.idPhieuDatVe = idPhieuDatVe;
		this.tenTuyen = tenTuyen;
		this.thoiDiemDi = thoiDiemDi;
		this.soLuongVe = soLuongVe;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
		this.danhSachIDGhe = danhSachIDGhe;
		this.hoTenNguoiDatVe = hoTenNguoiDatVe;
		SDTNguoiDatVe = sDTNguoiDatVe;
		EmailNguoiDatVe = emailNguoiDatVe;
		this.thoiGianDatVe = thoiGianDatVe;
		this.bienSoXe = bienSoXe;
		this.loaiXe = loaiXe;
		this.diaChiBenXeDi = diaChiBenXeDi;
		SDTBenXeDi = sDTBenXeDi;
		this.diaChiBenXeDen = diaChiBenXeDen;
	}
	public PurchaseItemResponse() {}
}