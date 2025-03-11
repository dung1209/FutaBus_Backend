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
    
    @Column(name = "idTinhThanh", nullable = false)
    private int idTinhThanh;

    public QuanHuyen() {
    }

    public QuanHuyen(int idQuanHuyen, String tenQuanHuyen, int idTinhThanh) {
        this.idTinhThanh = idTinhThanh;
        this.tenQuanHuyen = tenQuanHuyen;
        this.idTinhThanh = idTinhThanh;
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

	public int getIdTinhThanh() {
		return idTinhThanh;
	}

	public void setIdTinhThanh(int idTinhThanh) {
		this.idTinhThanh = idTinhThanh;
	}

	@Override
    public String toString() {
        return "QuanHuyen{" +
                "idQuanHuyen=" + idQuanHuyen +
                "tenQuanHuyen=" + tenQuanHuyen +
                ", idTinhThanh='" + idTinhThanh + '\'' +
                '}';
    }
}
