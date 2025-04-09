package FutaBus.bean;

import javax.persistence.*;

@Entity
@Table(name = "VeXe")
public class VeXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVe")
    private int idVe;

    @Column(name = "idPhieuDatVe", nullable = false)
    private int idPhieuDatVe;

    @Column(name = "trangThai", nullable = false)
    private int trangThai;

    @Column(name = "idViTriGhe", nullable = false)
    private int idViTriGhe;

    public VeXe() {
    }

    public VeXe(int idVe, int idPhieuDatVe, int trangThai, int idViTriGhe) {
        this.idVe = idVe;
        this.idPhieuDatVe = idPhieuDatVe;
        this.trangThai = trangThai;
        this.idViTriGhe = idViTriGhe;
    }

    public int getIdVe() {
        return idVe;
    }

    public void setIdVe(int idVe) {
        this.idVe = idVe;
    }

    public int getIdPhieuDatVe() {
        return idPhieuDatVe;
    }

    public void setIdPhieuDatVe(int idPhieuDatVe) {
        this.idPhieuDatVe = idPhieuDatVe;
    }

    public int isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdViTriGhe() {
        return idViTriGhe;
    }

    public void setIdViTriGhe(int idViTriGhe) {
        this.idViTriGhe = idViTriGhe;
    }

    @Override
    public String toString() {
        return "VeXe{" +
                "idVe=" + idVe +
                ", idPhieuDatVe=" + idPhieuDatVe +
                ", trangThai=" + trangThai +
                ", idViTriGhe=" + idViTriGhe +
                '}';
    }
}
