package FutaBus.bean;

public class XeUpdateDTO {
	
	private int idXe;
    private String tenXe;
    private String bienSo;
    private int idLoaiXe;
	public int getIdXe() {
		return idXe;
	}
	public void setIdXe(int idXe) {
		this.idXe = idXe;
	}
	public String getTenXe() {
		return tenXe;
	}
	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}
	public String getBienSo() {
		return bienSo;
	}
	public void setBienSo(String bienSo) {
		this.bienSo = bienSo;
	}
	public int getIdLoaiXe() {
		return idLoaiXe;
	}
	public void setIdLoaiXe(int idLoaiXe) {
		this.idLoaiXe = idLoaiXe;
	}
	@Override
	public String toString() {
		return "XeUpdateDTO [idXe=" + idXe + ", tenXe=" + tenXe + ", bienSo=" + bienSo + ", idLoaiXe=" + idLoaiXe + "]";
	}
	public XeUpdateDTO(int idXe, String tenXe, String bienSo, int idLoaiXe) {
		super();
		this.idXe = idXe;
		this.tenXe = tenXe;
		this.bienSo = bienSo;
		this.idLoaiXe = idLoaiXe;
	}
    
	public XeUpdateDTO() {}
    
}