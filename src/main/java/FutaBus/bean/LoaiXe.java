package FutaBus.bean;

import javax.persistence.*;

@Entity
@Table(name = "LoaiXe")
public class LoaiXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoaiXe")
    private int idLoaiXe;

    @Column(name = "tenLoai", nullable = false, length = 50)
    private String tenLoai;

    @Column(name = "soGhe", nullable = false)
    private int soGhe;

    public LoaiXe() {
    }
    
    public LoaiXe(int idLoaiXe, String tenLoai, int soGhe) {
    	this.idLoaiXe = idLoaiXe;
    	this.tenLoai = tenLoai;
    	this.soGhe = soGhe;
    }

	public int getIdLoaiXe() {
		return idLoaiXe;
	}

	public void setIdLoaiXe(int idLoaiXe) {
		this.idLoaiXe = idLoaiXe;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	@Override
    public String toString() {
        return "LoaiXe{" +
                "idLoaiXe=" + idLoaiXe +
                ", tenLoai='" + tenLoai + '\'' +
                ", soGhe=" + soGhe +
                '}';
    }
}
