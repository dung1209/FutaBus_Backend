package FutaBus.bean;

public class QuanHuyenUpdateDTO {
	
	private int idQuanHuyen;
    private String tenQuanHuyen;
    private int idTinhThanh;
    
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
	public int getIdTinhThanh() {
		return idTinhThanh;
	}
	public void setIdTinhThanh(int idTinhThanh) {
		this.idTinhThanh = idTinhThanh;
	}
	
	@Override
	public String toString() {
		return "QuanHuyenUpdateDTO [idQuanHuyen=" + idQuanHuyen + ", tenQuanHuyen=" + tenQuanHuyen + ", idTinhThanh="
				+ idTinhThanh + "]";
	}
	
	public QuanHuyenUpdateDTO(int idQuanHuyen, String tenQuanHuyen, int idTinhThanh) {
		super();
		this.idQuanHuyen = idQuanHuyen;
		this.tenQuanHuyen = tenQuanHuyen;
		this.idTinhThanh = idTinhThanh;
	}
    
	public QuanHuyenUpdateDTO() {}
	
}