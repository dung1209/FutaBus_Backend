package FutaBus.bean;

import javax.persistence.*;

@Entity
@Table(name = "QuanHuyen")
public class QuanHuyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuanHuyen")
    private int idQuanHuyen;

    @Column(name = "tenQuanHuyen", nullable = false, length = 255)
    private String tenQuanHuyen;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTinhThanh", nullable = false)
    private TinhThanh tinhThanh;
    
    @Column(name = "trangThai")
    private int trangThai;

    public QuanHuyen() {
    }

    public QuanHuyen(int idQuanHuyen, String tenQuanHuyen, TinhThanh tinhThanh, int trangThai) {
        this.idQuanHuyen = idQuanHuyen;
        this.tenQuanHuyen = tenQuanHuyen;
        this.tinhThanh = tinhThanh;
        this.trangThai = trangThai;
    }

    public int getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(int idQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public TinhThanh getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(TinhThanh tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
    public String toString() {
        return "QuanHuyen{" +
                "idQuanHuyen=" + idQuanHuyen +
                "trangThai=" + trangThai +
                ", tenQuanHuyen='" + tenQuanHuyen + '\'' +
                ", tinhThanh=" + (tinhThanh != null ? tinhThanh.getTenTinh() : "N/A") +
                '}';
    }
}

