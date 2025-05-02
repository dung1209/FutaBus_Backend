package FutaBus.bean;

import javax.persistence.*;

@Entity
@Table(name = "Xe")
public class Xe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idXe")
    private int idXe;

    @Column(name = "bienSo", nullable = false, length = 50)
    private String bienSo;

    @Column(name = "tenXe", nullable = false, length = 255)
    private String tenXe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLoaiXe", nullable = false)
    private LoaiXe loaiXe;
    
    @Column(name = "trangThai")
    private int trangThai;

    public Xe() {
    }

    public Xe(int idXe, String bienSo, String tenXe, LoaiXe loaiXe, int trangThai) {
        this.idXe = idXe;
        this.bienSo = bienSo;
        this.tenXe = tenXe;
        this.loaiXe = loaiXe;
        this.trangThai = trangThai;
    }

    public int getIdXe() {
        return idXe;
    }

    public void setIdXe(int idXe) {
        this.idXe = idXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public LoaiXe getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(LoaiXe loaiXe) {
        this.loaiXe = loaiXe;
    }

    @Override
    public String toString() {
        return "Xe{" +
                "idXe=" + idXe +
                ", bienSo='" + bienSo + '\'' +
                ", tenXe='" + tenXe + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", loaiXe=" + (loaiXe != null ? loaiXe.getTenLoai() : "null") +
                '}';
    }

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
}
