package FutaBus.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNguoiDung")
    private int idNguoiDung;

    @Column(name = "hoTen", nullable = false, length = 255)
    private String hoTen;

    @Column(name = "gioiTinh", nullable = false)
    private boolean gioiTinh;

    @Column(name = "namSinh", nullable = false)
    private int namSinh;

    @Column(name = "CCCD", nullable = false, length = 12)
    private String CCCD;

    @Column(name = "diaChi", nullable = true, length = 255)
    private String diaChi;

    @Column(name = "soDienThoai", nullable = false, length = 10)
    private String soDienThoai;

    @Column(name = "email", nullable = false, length = 320)
    private String email;

    @Column(name = "matKhau", nullable = false, length = 255)
    private String matKhau;

    @Column(name = "ngayDangKy", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayDangKy;

    @Column(name = "trangThai", nullable = false)
    private int trangThai;

    @Column(name = "idPhanQuyen", nullable = false)
    private int idPhanQuyen;

    public NguoiDung() {
    }

    public NguoiDung(int idNguoiDung, String hoTen, boolean gioiTinh, int namSinh, String CCCD, String diaChi,
                     String soDienThoai, String email, String matKhau, Date ngayDangKy, int trangThai, int idPhanQuyen) {
        this.idNguoiDung = idNguoiDung;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.matKhau = matKhau;
        this.ngayDangKy = ngayDangKy;
        this.trangThai = trangThai;
        this.idPhanQuyen = idPhanQuyen;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
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

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public int isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdPhanQuyen() {
        return idPhanQuyen;
    }

    public void setIdPhanQuyen(int idPhanQuyen) {
        this.idPhanQuyen = idPhanQuyen;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "idNguoiDung=" + idNguoiDung +
                ", hoTen='" + hoTen + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", namSinh=" + namSinh +
                ", CCCD='" + CCCD + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", ngayDangKy=" + ngayDangKy +
                ", trangThai=" + trangThai +
                ", idPhanQuyen=" + idPhanQuyen +
                '}';
    }
}
