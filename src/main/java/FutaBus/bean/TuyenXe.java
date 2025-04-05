package FutaBus.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TuyenXe")
public class TuyenXe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTuyenXe")
	private int idTuyenXe;

	@Column(name = "tenTuyen", nullable = false, length = 255)
	private String tenTuyen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTinhThanhDi")
	private TinhThanh tinhThanhDi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTinhThanhDen")
	private TinhThanh tinhThanhDen;

	public TinhThanh getTinhThanhDi() {
		return tinhThanhDi;
	}


	public void setTinhThanhDi(TinhThanh tinhThanhDi) {
		this.tinhThanhDi = tinhThanhDi;
	}
	


	public TinhThanh getTinhThanhDen() {
		return tinhThanhDen;
	}


	public void setTinhThanhDen(TinhThanh tinhThanhDen) {
		this.tinhThanhDen = tinhThanhDen;
	}



	@Column(name = "soNgayChayTrongTuan", nullable = false)
	private int soNgayChayTrongTuan;

	@Column(name = "soChuyenTrongNgay", nullable = false)
	private int soChuyenTrongNgay;

	@Column(name = "thoiGianDiChuyenTB", nullable = false)
	private float thoiGianDiChuyenTB;

	@Column(name = "giaHienHanh", nullable = true)
	private double giaHienHanh;

	@Column(name = "quangDuong", nullable = false)
	private float quangDuong;

	@ManyToOne
	@JoinColumn(name = "idBenXeDi", nullable = false)
	private BenXe benXeDi;

	@ManyToOne
	@JoinColumn(name = "idBenXeDen", nullable = false)
	private BenXe benXeDen;


	public TuyenXe() {
	}


	public int getIdTuyenXe() {
		return idTuyenXe;
	}


	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}


	public String getTenTuyen() {
		return tenTuyen;
	}


	public void setTenTuyen(String tenTuyen) {
		this.tenTuyen = tenTuyen;
	}


	public int getSoNgayChayTrongTuan() {
		return soNgayChayTrongTuan;
	}


	public void setSoNgayChayTrongTuan(int soNgayChayTrongTuan) {
		this.soNgayChayTrongTuan = soNgayChayTrongTuan;
	}


	public int getSoChuyenTrongNgay() {
		return soChuyenTrongNgay;
	}


	public void setSoChuyenTrongNgay(int soChuyenTrongNgay) {
		this.soChuyenTrongNgay = soChuyenTrongNgay;
	}


	public float getThoiGianDiChuyenTB() {
		return thoiGianDiChuyenTB;
	}


	public void setThoiGianDiChuyenTB(float thoiGianDiChuyenTB) {
		this.thoiGianDiChuyenTB = thoiGianDiChuyenTB;
	}


	public double getGiaHienHanh() {
		return giaHienHanh;
	}


	public void setGiaHienHanh(double giaHienHanh) {
		this.giaHienHanh = giaHienHanh;
	}


	public float getQuangDuong() {
		return quangDuong;
	}


	public void setQuangDuong(float quangDuong) {
		this.quangDuong = quangDuong;
	}


	public BenXe getBenXeDi() {
		return benXeDi;
	}


	public void setBenXeDi(BenXe benXeDi) {
		this.benXeDi = benXeDi;
	}


	public BenXe getBenXeDen() {
		return benXeDen;
	}


	public void setBenXeDen(BenXe benXeDen) {
		this.benXeDen = benXeDen;
	}


	public TuyenXe(int idTuyenXe, String tenTuyen, TinhThanh tinhThanhDi, TinhThanh tinhThanhDen,
			int soNgayChayTrongTuan, int soChuyenTrongNgay, float thoiGianDiChuyenTB, double giaHienHanh,
			float quangDuong, BenXe benXeDi, BenXe benXeDen) {
		super();
		this.idTuyenXe = idTuyenXe;
		this.tenTuyen = tenTuyen;
		this.tinhThanhDi = tinhThanhDi;
		this.tinhThanhDen = tinhThanhDen;
		this.soNgayChayTrongTuan = soNgayChayTrongTuan;
		this.soChuyenTrongNgay = soChuyenTrongNgay;
		this.thoiGianDiChuyenTB = thoiGianDiChuyenTB;
		this.giaHienHanh = giaHienHanh;
		this.quangDuong = quangDuong;
		this.benXeDi = benXeDi;
		this.benXeDen = benXeDen;
	}


	@Override
	public String toString() {
		return "TuyenXe [idTuyenXe=" + idTuyenXe + ", tenTuyen=" + tenTuyen + ", tinhThanhDi=" + tinhThanhDi
				+ ", tinhThanhDen=" + tinhThanhDen + ", soNgayChayTrongTuan=" + soNgayChayTrongTuan
				+ ", soChuyenTrongNgay=" + soChuyenTrongNgay + ", thoiGianDiChuyenTB=" + thoiGianDiChuyenTB
				+ ", giaHienHanh=" + giaHienHanh + ", quangDuong=" + quangDuong + ", benXeDi=" + benXeDi + ", benXeDen="
				+ benXeDen + "]";
	}



	
	

	
}






//public class TuyenXe {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "idTuyenXe")
//	private int idTuyenXe;
//
//	@Column(name = "tenTuyen", nullable = false, length = 255)
//	private String tenTuyen;
//
//	@Column(name = "soNgayChayTrongTuan", nullable = false)
//	private int soNgayChayTrongTuan;
//
//	@Column(name = "soChuyenTrongNgay", nullable = false)
//	private int soChuyenTrongNgay;
//
//	@Column(name = "thoiGianDiChuyenTB", nullable = false)
//	private float thoiGianDiChuyenTB;
//
//	@Column(name = "giaHienHanh", nullable = true)
//	private double giaHienHanh;
//
//	@Column(name = "quangDuong", nullable = false)
//	private float quangDuong;
//
//	@Column(name = "idBenXeDi", nullable = false)
//	private int idBenXeDi;
//
//	@Column(name = "idBenXeDen", nullable = false)
//	private int idBenXeDen;
//
//	public TuyenXe() {
//	}
//
//	public TuyenXe(int idTuyenXe, String tenTuyen, int soNgayChayTrongTuan, int soChuyenTrongNgay, float thoiGianDiChuyenTB, double giaHienHanh,
//            float quangDuong, int idBenXeDi, int idBenXeDen) {
//		this.idTuyenXe = idTuyenXe;
//		this.tenTuyen = tenTuyen;
//		this.soNgayChayTrongTuan = soNgayChayTrongTuan;
//		this.soChuyenTrongNgay = soChuyenTrongNgay;
//		this.thoiGianDiChuyenTB = thoiGianDiChuyenTB;
//		this.giaHienHanh = giaHienHanh;
//		this.quangDuong = quangDuong;
//		this.idBenXeDi = idBenXeDi;
//		this.idBenXeDen = idBenXeDen;
//	}
//
//	public int getIdTuyenXe() {
//		return idTuyenXe;
//	}
//
//	public void setIdTuyenXe(int idTuyenXe) {
//		this.idTuyenXe = idTuyenXe;
//	}
//
//	public String getTenTuyen() {
//		return tenTuyen;
//	}
//
//	public void setTenTuyen(String tenTuyen) {
//		this.tenTuyen = tenTuyen;
//	}
//
//	public int getSoNgayChayTrongTuan() {
//		return soNgayChayTrongTuan;
//	}
//
//	public void setSoNgayChayTrongTuan(int soNgayChayTrongTuan) {
//		this.soNgayChayTrongTuan = soNgayChayTrongTuan;
//	}
//
//	public int getSoChuyenTrongNgay() {
//		return soChuyenTrongNgay;
//	}
//
//	public void setSoChuyenTrongNgay(int soChuyenTrongNgay) {
//		this.soChuyenTrongNgay = soChuyenTrongNgay;
//	}
//
//	public float getThoiGianDiChuyenTB() {
//		return thoiGianDiChuyenTB;
//	}
//
//	public void setThoiGianDiChuyenTB(float thoiGianDiChuyenTB) {
//		this.thoiGianDiChuyenTB = thoiGianDiChuyenTB;
//	}
//
//	public double getGiaHienHanh() {
//		return giaHienHanh;
//	}
//
//	public void setGiaHienHanh(double giaHienHanh) {
//		this.giaHienHanh = giaHienHanh;
//	}
//
//	public float getQuangDuong() {
//		return quangDuong;
//	}
//
//	public void setQuangDuong(float quangDuong) {
//		this.quangDuong = quangDuong;
//	}
//
//	public int getIdBenXeDi() {
//		return idBenXeDi;
//	}
//
//	public void setIdBenXeDi(int idBenXeDi) {
//		this.idBenXeDi = idBenXeDi;
//	}
//
//	public int getIdBenXeDen() {
//		return idBenXeDen;
//	}
//
//	public void setIdBenXeDen(int idBenXeDen) {
//		this.idBenXeDen = idBenXeDen;
//	}
//
//	@Override
//	public String toString() {
//		return "TuyenXe{" + "idTuyenXe=" + idTuyenXe + ", tenTuyen='" + tenTuyen + '\'' + ", soNgayChayTrongTuan="
//				+ soNgayChayTrongTuan + ", soChuyenTrongNgay=" + soChuyenTrongNgay + ", thoiGianDiChuyenTB='"
//				+ thoiGianDiChuyenTB + '\'' + ", giaHienHanh='" + giaHienHanh + '\'' + ", quangDuong='" + quangDuong
//				+ '\'' + ", idBenXeDi='" + idBenXeDi + '\'' + ", idBenXeDen='" + idBenXeDen + '\'' + '}';
//	}
//}
