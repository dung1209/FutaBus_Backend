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

    @Column(name = "idQuanHuyen", nullable = false)
    private int idQuanHuyen;

    public BenXe() {
    }
    
    public BenXe(int idBenXe, String tenBenXe, String diaChi, String soDienThoai, int idQuanHuyen) {
    	this.idBenXe = idBenXe;
    	this.tenBenXe = tenBenXe;
    	this.diaChi = diaChi;
    	this.soDienThoai = soDienThoai;
    	this.idQuanHuyen = idQuanHuyen;
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

	public int getIdQuanHuyen() {
		return idQuanHuyen;
	}

	public void setIdQuanHuyen(int idQuanHuyen) {
		this.idQuanHuyen = idQuanHuyen;
	}

	@Override
    public String toString() {
        return "BenXe{" +
                "idBenXe=" + idBenXe +
                ", tenBenXe='" + tenBenXe + '\'' +
                ", diaChi=" + diaChi +
                ", soDienThoai=" + soDienThoai +
                ", idQuanHuyen='" + idQuanHuyen + '\'' +
                '}';
    }
}
