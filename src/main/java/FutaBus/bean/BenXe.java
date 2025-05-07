package FutaBus.bean;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "BenXe")
public class BenXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBenXe")
    private int idBenXe;

    @Column(name = "tenBenXe", nullable = false, length = 255)
    private String tenBenXe;

    @Column(name = "diaChi", nullable = false, length = 255)
    private String diaChi;

    @Column(name = "soDienThoai", nullable = false, length = 10)
    private String soDienThoai;

    @ManyToOne
    @JoinColumn(name = "idQuanHuyen", referencedColumnName = "idQuanHuyen")
    private QuanHuyen quanHuyen;
    
    @Column(name = "trangThai", nullable = false)
    private int trangThai;

    public BenXe() {
    }
    
    public BenXe(int idBenXe, String tenBenXe, String diaChi, String soDienThoai, QuanHuyen quanHuyen, int thangThai) {
    	this.idBenXe = idBenXe;
    	this.tenBenXe = tenBenXe;
    	this.diaChi = diaChi;
    	this.soDienThoai = soDienThoai;
    	this.quanHuyen = quanHuyen;
    	this.trangThai = thangThai;
    }

    public int getIdBenXe() {
		return idBenXe;
	}

	public void setIdBenXe(int idBenXe) {
		this.idBenXe = idBenXe;
	}

	public String getTenBenXe() {
		return tenBenXe;
	}

	public void setTenBenXe(String tenBenXe) {
		this.tenBenXe = tenBenXe;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public QuanHuyen getQuanHuyen() {
		return quanHuyen;
	}

	public void setQuanHuyen(QuanHuyen quanHuyen) {
		this.quanHuyen = quanHuyen;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
    public String toString() {
        return "BenXe{" +
                "idBenXe=" + idBenXe +
                ", tenBenXe='" + tenBenXe + '\'' +
                ", diaChi=" + diaChi +
                ", soDienThoai=" + soDienThoai +
                ", trangThai=" + trangThai +
                ", quanHuyen=" + (quanHuyen != null ? quanHuyen.getTenQuanHuyen() : "N/A") +
                '}';
    }
}
