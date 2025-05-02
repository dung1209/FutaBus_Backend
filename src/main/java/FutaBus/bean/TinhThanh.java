package FutaBus.bean;

import javax.persistence.*;

@Entity
@Table(name = "TinhThanh")
public class TinhThanh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTinhThanh")
    private int idTinhThanh;

    @Column(name = "tenTinh", nullable = false, length = 255)
    private String tenTinh;
    
    @Column(name = "trangThai")
    private int trangThai;

    public TinhThanh() {
    }

    public TinhThanh(int idTinhThanh, String tenTinh, int trangThai) {
        this.idTinhThanh = idTinhThanh;
        this.tenTinh = tenTinh;
        this.trangThai = trangThai;
    }

    public int getIdTinhThanh() {
        return idTinhThanh;
    }

    public void setIdPhanQuyen(int idTinhThanh) {
        this.idTinhThanh = idTinhThanh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
    
    public void setIdTinhThanh(int idTinhThanh) {
        this.idTinhThanh = idTinhThanh;
    }

    public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
    public String toString() {
        return "TinhThanh{" +
                "idTinhThanh=" + idTinhThanh +
                "trangThai=" + trangThai +
                ", tenTinh='" + tenTinh + '\'' +
                '}';
    }
}
