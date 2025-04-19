package FutaBus.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class PurchaseHistory {
	
	private int idPhieuDatVe;
	private String tenTuyen;
	private Date thoiDiemDi;
    private int soLuongVe;
    private BigDecimal tongTien;
    private int trangThai;
    
    public PurchaseHistory() {}

	public PurchaseHistory(int idPhieuDatVe, String tenTuyen, Date date, int soLuongVe, BigDecimal tongTien,
			int trangThai) {
		super();
		this.idPhieuDatVe = idPhieuDatVe;
		this.tenTuyen = tenTuyen;
		this.thoiDiemDi = date;
		this.soLuongVe = soLuongVe;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}

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

	@Override
	public String toString() {
		return "PurchaseHistory [idPhieuDatVe=" + idPhieuDatVe + ", tenTuyen=" + tenTuyen + ", thoiDiemDi=" + thoiDiemDi
				+ ", soLuongVe=" + soLuongVe + ", tongTien=" + tongTien + ", trangThai=" + trangThai + "]";
	}
	
}