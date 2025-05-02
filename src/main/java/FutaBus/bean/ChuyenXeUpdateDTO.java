package FutaBus.bean;

public class ChuyenXeUpdateDTO {
	
	private int idChuyenXe;
	private int idTuyenXe;
	private String thoiDiemDi;
	private String thoiDiemDen;
	private int idXe;
	private double giaVe;
	private int idTaiXe;
	private int trangThai;
	
	public int getIdChuyenXe() {
		return idChuyenXe;
	}
	
	public void setIdChuyenXe(int idChuyenXe) {
		this.idChuyenXe = idChuyenXe;
	}
	
	public int getIdTuyenXe() {
		return idTuyenXe;
	}
	
	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}
	
	public String getThoiDiemDi() {
		return thoiDiemDi;
	}
	
	public void setThoiDiemDi(String thoiDiemDi) {
		this.thoiDiemDi = thoiDiemDi;
	}
	
	public String getThoiDiemDen() {
		return thoiDiemDen;
	}
	
	public void setThoiDiemDen(String thoiDiemDen) {
		this.thoiDiemDen = thoiDiemDen;
	}
	
	public int getIdXe() {
		return idXe;
	}
	
	public void setIdXe(int idXe) {
		this.idXe = idXe;
	}
	
	public double getGiaVe() {
		return giaVe;
	}
	
	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}
	
	public int getIdTaiXe() {
		return idTaiXe;
	}
	
	public void setIdTaiXe(int idTaiXe) {
		this.idTaiXe = idTaiXe;
	}
	
	public int getTrangThai() {
		return trangThai;
	}
	
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	@Override
	public String toString() {
		return "ChuyenXeUpdateDTO [idChuyenXe=" + idChuyenXe + ", idTuyenXe=" + idTuyenXe + ", thoiDiemDi=" + thoiDiemDi
				+ ", thoiDiemDen=" + thoiDiemDen + ", idXe=" + idXe + ", giaVe=" + giaVe + ", idTaiXe=" + idTaiXe
				+ ", trangThai=" + trangThai + "]";
	}
	
	public ChuyenXeUpdateDTO(int idChuyenXe, int idTuyenXe, String thoiDiemDi, String thoiDiemDen, int idXe,
			double giaVe, int idTaiXe, int trangThai) {
		super();
		this.idChuyenXe = idChuyenXe;
		this.idTuyenXe = idTuyenXe;
		this.thoiDiemDi = thoiDiemDi;
		this.thoiDiemDen = thoiDiemDen;
		this.idXe = idXe;
		this.giaVe = giaVe;
		this.idTaiXe = idTaiXe;
		this.trangThai = trangThai;
	}
	
	public ChuyenXeUpdateDTO() {}

}