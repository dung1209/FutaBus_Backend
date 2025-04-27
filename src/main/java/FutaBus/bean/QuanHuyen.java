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

    public QuanHuyen() {
    }

    public QuanHuyen(int idQuanHuyen, String tenQuanHuyen, TinhThanh tinhThanh) {
        this.idQuanHuyen = idQuanHuyen;
        this.tenQuanHuyen = tenQuanHuyen;
        this.tinhThanh = tinhThanh;
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

    @Override
    public String toString() {
        return "QuanHuyen{" +
                "idQuanHuyen=" + idQuanHuyen +
                ", tenQuanHuyen='" + tenQuanHuyen + '\'' +
                ", tinhThanh=" + (tinhThanh != null ? tinhThanh.getTenTinh() : "N/A") +
                '}';
    }
}

