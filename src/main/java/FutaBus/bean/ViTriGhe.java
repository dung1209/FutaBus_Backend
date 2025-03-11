package FutaBus.bean;

import javax.persistence.*;

@Entity
@Table(name = "ViTriGhe")
public class ViTriGhe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idViTriGhe")
    private int idViTriGhe;

    @Column(name = "tenViTri", nullable = false, length = 3)
    private String tenViTri;

    @Column(name = "trangThai", nullable = false)
    private int trangThai;

    @Column(name = "idXe", nullable = false)
    private int idXe;

    public ViTriGhe() {
    }

    public ViTriGhe(int idViTriGhe, String tenViTri, int trangThai, int idXe) {
        this.idViTriGhe = idViTriGhe;
        this.tenViTri = tenViTri;
        this.trangThai = trangThai;
        this.idXe = idXe;
    }

    public int getIdViTriGhe() {
        return idViTriGhe;
    }

    public void setIdViTriGhe(int idViTriGhe) {
        this.idViTriGhe = idViTriGhe;
    }

    public String getTenViTri() {
        return tenViTri;
    }

    public void setTenViTri(String tenViTri) {
        this.tenViTri = tenViTri;
    }

    public int isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdXe() {
        return idXe;
    }

    public void setIdXe(int idXe) {
        this.idXe = idXe;
    }

    @Override
    public String toString() {
        return "ViTriGhe{" +
                "idViTriGhe=" + idViTriGhe +
                ", tenViTri='" + tenViTri + '\'' +
                ", trangThai=" + trangThai +
                ", idXe=" + idXe +
                '}';
    }
}
