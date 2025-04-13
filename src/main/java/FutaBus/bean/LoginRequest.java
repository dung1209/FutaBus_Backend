package FutaBus.bean;

public class LoginRequest {
    private String soDienThoai;
    private String matKhau;


    public LoginRequest() {
	}

	public LoginRequest(String soDienThoai, String matKhau) {
		super();
		this.soDienThoai = soDienThoai;
		this.matKhau = matKhau;
	}

	public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}

