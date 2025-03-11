package FutaBus.bean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "LoTrinh")
public class LoTrinh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoTrinh")
    private int idLoTrinh;

    @Column(name = "idBenXeTrungGian", nullable = false)
    private int idBenXeTrungGian;

    @Column(name = "thuTuDung", nullable = false)
    private int thuTuDung;

    @Column(name = "giaTien", nullable = false, precision = 10, scale = 2)
    private BigDecimal giaTien;

    @Column(name = "idTuyenXe", nullable = false)
    private int idTuyenXe;

    public LoTrinh() {
    }

    public LoTrinh(int idLoTrinh, int idBenXeTrungGian, int thuTuDung, BigDecimal giaTien, int idTuyenXe) {
        this.idLoTrinh = idLoTrinh;
        this.idBenXeTrungGian = idBenXeTrungGian;
        this.thuTuDung = thuTuDung;
        this.giaTien = giaTien;
        this.idTuyenXe = idTuyenXe;
    }

    public int getIdLoTrinh() {
        return idLoTrinh;
    }

    public void setIdLoTrinh(int idLoTrinh) {
        this.idLoTrinh = idLoTrinh;
    }

    public int getIdBenXeTrungGian() {
        return idBenXeTrungGian;
    }

    public void setIdBenXeTrungGian(int idBenXeTrungGian) {
        this.idBenXeTrungGian = idBenXeTrungGian;
    }

    public int getThuTuDung() {
        return thuTuDung;
    }

    public void setThuTuDung(int thuTuDung) {
        this.thuTuDung = thuTuDung;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public int getIdTuyenXe() {
        return idTuyenXe;
    }

    public void setIdTuyenXe(int idTuyenXe) {
        this.idTuyenXe = idTuyenXe;
    }

    @Override
    public String toString() {
        return "LoTrinh{" +
                "idLoTrinh=" + idLoTrinh +
                ", idBenXeTrungGian=" + idBenXeTrungGian +
                ", thuTuDung=" + thuTuDung +
                ", giaTien=" + giaTien +
                ", idTuyenXe=" + idTuyenXe +
                '}';
    }
}
