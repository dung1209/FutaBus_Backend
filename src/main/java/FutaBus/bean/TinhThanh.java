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

    public TinhThanh() {
    }

    public TinhThanh(int idTinhThanh, String tenTinh) {
        this.idTinhThanh = idTinhThanh;
        this.tenTinh = tenTinh;
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

    @Override
    public String toString() {
        return "TinhThanh{" +
                "idTinhThanh=" + idTinhThanh +
                ", tenTinh='" + tenTinh + '\'' +
                '}';
    }
}
