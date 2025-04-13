package FutaBus.bean;

public class VerifyOtpRequest {
//	private String email;
//    private String otp;

//    public VerifyOtpRequest() {
//		super();
//	}
//
//	public VerifyOtpRequest(String email, String otp) {
//		super();
//		this.email = email;
//		this.otp = otp;
//	}
//
//	public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getOtp() {
//        return otp;
//    }
//
//    public void setOtp(String otp) {
//        this.otp = otp;
//    }
    private String email;
    private String otp;
    private String hoTen;
    private String soDienThoai;
    private String matKhau;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }
}