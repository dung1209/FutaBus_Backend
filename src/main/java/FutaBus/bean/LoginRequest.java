package FutaBus.bean;

public class LoginRequest {
    private String email;
    private String matKhau;


    public LoginRequest() {
	}


	public LoginRequest(String email, String matKhau) {
		super();
		this.email = email;
		this.matKhau = matKhau;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMatKhau() {
		return matKhau;
	}


	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	
}

