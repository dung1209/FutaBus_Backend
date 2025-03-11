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

	@Column(name = "idBenXeDi", nullable = false)
	private int idBenXeDi;

	@Column(name = "idBenXeDen", nullable = false)
	private int idBenXeDen;

	public TuyenXe() {
	}

	public TuyenXe(int idTuyenXe, String tenTuyen, int soNgayChayTrongTuan, int soChuyenTrongNgay, float thoiGianDiChuyenTB, double giaHienHanh,
            float quangDuong, int idBenXeDi, int idBenXeDen) {
		this.idTuyenXe = idTuyenXe;
		this.tenTuyen = tenTuyen;
		this.soNgayChayTrongTuan = soNgayChayTrongTuan;
		this.soChuyenTrongNgay = soChuyenTrongNgay;
		this.thoiGianDiChuyenTB = thoiGianDiChuyenTB;
		this.giaHienHanh = giaHienHanh;
		this.quangDuong = quangDuong;
		this.idBenXeDi = idBenXeDi;
		this.idBenXeDen = idBenXeDen;
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

	public int getIdBenXeDi() {
		return idBenXeDi;
	}

	public void setIdBenXeDi(int idBenXeDi) {
		this.idBenXeDi = idBenXeDi;
	}

	public int getIdBenXeDen() {
		return idBenXeDen;
	}

	public void setIdBenXeDen(int idBenXeDen) {
		this.idBenXeDen = idBenXeDen;
	}

	@Override
	public String toString() {
		return "TuyenXe{" + "idTuyenXe=" + idTuyenXe + ", tenTuyen='" + tenTuyen + '\'' + ", soNgayChayTrongTuan="
				+ soNgayChayTrongTuan + ", soChuyenTrongNgay=" + soChuyenTrongNgay + ", thoiGianDiChuyenTB='"
				+ thoiGianDiChuyenTB + '\'' + ", giaHienHanh='" + giaHienHanh + '\'' + ", quangDuong='" + quangDuong
				+ '\'' + ", idBenXeDi='" + idBenXeDi + '\'' + ", idBenXeDen='" + idBenXeDen + '\'' + '}';
	}
}
