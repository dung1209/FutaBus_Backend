package FutaBus.bean;

import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ChuyenXe")
public class ChuyenXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChuyenXe")
    private int idChuyenXe;

    @Column(name = "thoiDiemDi", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date thoiDiemDi;

    @Column(name = "thoiDiemDen", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date thoiDiemDen;

    @Column(name = "giaVe", nullable = false)
    private double giaVe;

    @Column(name = "trangThai", nullable = false)
    private int trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTuyenXe", nullable = false)
    private TuyenXe tuyenXe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idXe", nullable = true)
    private Xe xe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTaiXe", nullable = false)
    private NguoiDung taiXe;

    public TuyenXe getTuyenXe() {
        return tuyenXe;
    }

    public void setTuyenXe(TuyenXe tuyenXe) {
        this.tuyenXe = tuyenXe;
    }

    public Xe getXe() {
        return xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
    }

    public NguoiDung getTaiXe() {
        return taiXe;
    }

    public void setTaiXe(NguoiDung taiXe) {
        this.taiXe = taiXe;
    }

    public int getIdChuyenXe() {
		return idChuyenXe;
	}

	public void setIdChuyenXe(int idChuyenXe) {
		this.idChuyenXe = idChuyenXe;
	}

	public Date getThoiDiemDi() {
		return thoiDiemDi;
	}

	public void setThoiDiemDi(Date thoiDiemDi) {
		this.thoiDiemDi = thoiDiemDi;
	}

	public Date getThoiDiemDen() {
		return thoiDiemDen;
	}

	public void setThoiDiemDen(Date thoiDiemDen) {
		this.thoiDiemDen = thoiDiemDen;
	}

	public double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
    public String toString() {
        return "ChuyenXe{" +
                "idChuyenXe=" + idChuyenXe +
                ", thoiDiemDi=" + thoiDiemDi +
                ", thoiDiemDen=" + thoiDiemDen +
                ", giaVe=" + giaVe +
                ", trangThai=" + trangThai +
                ", tuyenXe=" + tuyenXe.getTenTuyen() +
                ", xe=" + xe.getBienSo() +
                ", taiXe=" + taiXe.getHoTen() +
                '}';
    }
}

