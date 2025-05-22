package FutaBus.bean;

public class BenXeDTO {
    private int idBenXe;
    private String tenBenXe;
    private String diaChi;
    private String soDienThoai;
    private int idQuanHuyen;
    private int trangThai;

    public BenXeDTO() {}

    public BenXeDTO(int idBenXe, String tenBenXe, String diaChi, String soDienThoai, int idQuanHuyen, int trangThai) {
        this.idBenXe = idBenXe;
        this.tenBenXe = tenBenXe;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.idQuanHuyen = idQuanHuyen;
        this.trangThai = trangThai;
    }

	public int getIdBenXe() {
		return idBenXe;
	}

	public void setIdBenXe(int idBenXe) {
		this.idBenXe = idBenXe;
	}

	public String getTenBenXe() {
		return tenBenXe;
	}

	public void setTenBenXe(String tenBenXe) {
		this.tenBenXe = tenBenXe;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public int getIdQuanHuyen() {
		return idQuanHuyen;
	}

	public void setIdQuanHuyen(int idQuanHuyen) {
		this.idQuanHuyen = idQuanHuyen;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "BenXeDTO [idBenXe=" + idBenXe + ", tenBenXe=" + tenBenXe + ", diaChi=" + diaChi + ", soDienThoai="
				+ soDienThoai + ", idQuanHuyen=" + idQuanHuyen + ", trangThai=" + trangThai + "]";
	}
}