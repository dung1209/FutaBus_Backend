package FutaBus.bean;

public class OtpRequest {
	private String email;

    public OtpRequest() {
		super();
	}

	public OtpRequest(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}