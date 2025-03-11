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
    
    @Column(name = "giaVe", nullable = false, precision = 10, scale = 2)
    private BigDecimal giaVe;
    
    @Column(name = "trangThai", nullable = false)
    private int trangThai;

    @Column(name = "idTuyenXe", nullable = false)
    private int idTuyenXe;

    @Column(name = "idXe", nullable = true)
    private int idXe;

    @Column(name = "idTaiXe", nullable = false)
    private int idTaiXe;

    public ChuyenXe() {
    }

    public ChuyenXe(int idChuyenXe, Date thoiDiemDi, Date thoiDiemDen, BigDecimal giaVe, int trangThai, int idTuyenXe, int idXe, int idTaiXe) {
        this.idChuyenXe = idChuyenXe;
        this.thoiDiemDi = thoiDiemDi;
        this.thoiDiemDen = thoiDiemDen;
        this.giaVe = giaVe;
        this.trangThai = trangThai;
        this.idTuyenXe = idTuyenXe;
        this.idXe = idXe;
        this.idTaiXe = idTaiXe;
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

	public BigDecimal getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(BigDecimal giaVe) {
		this.giaVe = giaVe;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public int getIdTuyenXe() {
		return idTuyenXe;
	}

	public void setIdTuyenXe(int idTuyenXe) {
		this.idTuyenXe = idTuyenXe;
	}

	public int getIdXe() {
		return idXe;
	}

	public void setIdXe(int idXe) {
		this.idXe = idXe;
	}

	public int getIdTaiXe() {
		return idTaiXe;
	}

	public void setIdTaiXe(int idTaiXe) {
		this.idTaiXe = idTaiXe;
	}

	@Override
	public String toString() {
	    return "ChuyenXe{" +
	            "idChuyenXe=" + idChuyenXe +
	            ", thoiDiemDi=" + thoiDiemDi +
	            ", thoiDiemDen=" + thoiDiemDen +
	            ", giaVe=" + giaVe +
	            ", trangThai=" + trangThai +
	            ", idTuyenXe=" + idTuyenXe +
	            ", idXe=" + idXe +
	            ", idTaiXe=" + idTaiXe +
	            '}';
	}
}
