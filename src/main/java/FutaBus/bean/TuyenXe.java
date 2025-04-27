package FutaBus.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TuyenXe")
public class TuyenXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTuyenXe")
    private int idTuyenXe;

    @Column(name = "tenTuyen", nullable = false, length = 255)
    private String tenTuyen;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTinhThanhDi")
    private TinhThanh tinhThanhDi;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTinhThanhDen")
    private TinhThanh tinhThanhDen;

    @Column(name = "soNgayChayTrongTuan", nullable = false)
    private int soNgayChayTrongTuan;

    @Column(name = "soChuyenTrongNgay", nullable = false)
    private int soChuyenTrongNgay;

    @Column(name = "thoiGianDiChuyenTB", nullable = false)
    private float thoiGianDiChuyenTB;

    @Column(name = "quangDuong", nullable = false)
    private float quangDuong;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idBenXeDi", nullable = false)
    private BenXe benXeDi; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idBenXeDen", nullable = false)
    private BenXe benXeDen; 

    public TuyenXe() {
    }

    public TuyenXe(int idTuyenXe, String tenTuyen, int soNgayChayTrongTuan, int soChuyenTrongNgay, float thoiGianDiChuyenTB, 
                   float quangDuong, BenXe benXeDi, BenXe benXeDen) {
        this.idTuyenXe = idTuyenXe;
        this.tenTuyen = tenTuyen;
        this.soNgayChayTrongTuan = soNgayChayTrongTuan;
        this.soChuyenTrongNgay = soChuyenTrongNgay;
        this.thoiGianDiChuyenTB = thoiGianDiChuyenTB;
        this.quangDuong = quangDuong;
        this.benXeDi = benXeDi;  
        this.benXeDen = benXeDen; 
    }

    public int getIdTuyenXe() {
        return idTuyenXe;
    }

    public void setIdTuyenXe(int idTuyenXe) {
        this.idTuyenXe = idTuyenXe;
    }

    public String getTenTuyen() {
        return tenTuyen;
    }

    public void setTenTuyen(String tenTuyen) {
        this.tenTuyen = tenTuyen;
    }

    public int getSoNgayChayTrongTuan() {
        return soNgayChayTrongTuan;
    }

    public void setSoNgayChayTrongTuan(int soNgayChayTrongTuan) {
        this.soNgayChayTrongTuan = soNgayChayTrongTuan;
    }

    public int getSoChuyenTrongNgay() {
        return soChuyenTrongNgay;
    }

    public void setSoChuyenTrongNgay(int soChuyenTrongNgay) {
        this.soChuyenTrongNgay = soChuyenTrongNgay;
    }

    public float getThoiGianDiChuyenTB() {
        return thoiGianDiChuyenTB;
    }

    public void setThoiGianDiChuyenTB(float thoiGianDiChuyenTB) {
        this.thoiGianDiChuyenTB = thoiGianDiChuyenTB;
    }

    public float getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(float quangDuong) {
        this.quangDuong = quangDuong;
    }

    public BenXe getBenXeDi() {
        return benXeDi;
    }

    public void setBenXeDi(BenXe benXeDi) {
        this.benXeDi = benXeDi;
    }

    public BenXe getBenXeDen() {
        return benXeDen;
    }

    public void setBenXeDen(BenXe benXeDen) {
        this.benXeDen = benXeDen;
    }

    public TinhThanh getTinhThanhDi() {
        return tinhThanhDi;
    }

    public void setTinhThanhDi(TinhThanh tinhThanhDi) {
        this.tinhThanhDi = tinhThanhDi;
    }

    public TinhThanh getTinhThanhDen() {
        return tinhThanhDen;
    }

    public void setTinhThanhDen(TinhThanh tinhThanhDen) {
        this.tinhThanhDen = tinhThanhDen;
    }

	/*@Override
	public String toString() {
		return "TuyenXe [idTuyenXe=" + idTuyenXe + ", tenTuyen=" + tenTuyen + ", tinhThanhDi=" + tinhThanhDi
				+ ", tinhThanhDen=" + tinhThanhDen + ", soNgayChayTrongTuan=" + soNgayChayTrongTuan
				+ ", soChuyenTrongNgay=" + soChuyenTrongNgay + ", thoiGianDiChuyenTB=" + thoiGianDiChuyenTB
				+ ", quangDuong=" + quangDuong + ", benXeDi=" + benXeDi + ", benXeDen=" + benXeDen + "]";
	}*/
    @Override
    public String toString() {
        return "TuyenXe [idTuyenXe=" + idTuyenXe 
                + ", tenTuyen=" + tenTuyen 
                + ", idTinhThanhDi=" + (tinhThanhDi != null ? tinhThanhDi.getIdTinhThanh() : "null")
                + ", idTinhThanhDen=" + (tinhThanhDen != null ? tinhThanhDen.getIdTinhThanh() : "null")
                + ", soNgayChayTrongTuan=" + soNgayChayTrongTuan
                + ", soChuyenTrongNgay=" + soChuyenTrongNgay 
                + ", thoiGianDiChuyenTB=" + thoiGianDiChuyenTB
                + ", quangDuong=" + quangDuong 
                + ", idBenXeDi=" + (benXeDi != null ? benXeDi.getIdBenXe() : "null")
                + ", idBenXeDen=" + (benXeDen != null ? benXeDen.getIdBenXe() : "null")
                + "]";
    }

    
}

