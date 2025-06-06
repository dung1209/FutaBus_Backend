package SpringMVC.ApiController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.mail.Session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Dao.BenXeDao;
import Dao.ChuyenXeDao;
import Dao.LoaiXeDao;
import Dao.NguoiDungDao;
import Dao.PhieuDatVeDao;
import Dao.QuanHuyenDao;
import Dao.TinhThanhDao;
import Dao.TuyenXeDao;
import Dao.ViTriGheDao;
import Dao.XeDao;
import FutaBus.bean.BenXe;
import FutaBus.bean.BenXeDTO;
import FutaBus.bean.BookingInfo;
import FutaBus.bean.ChuyenXe;
import FutaBus.bean.ChuyenXeUpdateDTO;
import FutaBus.bean.NguoiDung;
import FutaBus.bean.PhieuDatVe;
import FutaBus.bean.QuanHuyen;
import FutaBus.bean.QuanHuyenUpdateDTO;
import FutaBus.bean.TinhThanh;
import FutaBus.bean.TuyenXe;
import FutaBus.bean.TuyenXeUpdateDTO;
import FutaBus.bean.Xe;
import FutaBus.bean.XeUpdateDTO;
import FutaBus.bean.LoaiXe;

@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private static final int PAGE_SIZE = 5;

    @GetMapping("/nguoidung")
    public Map<String, Object> getNguoiDung(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "loaiNguoiDung", defaultValue = "1") int loaiNguoiDung) {

        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        XeDao xeDao = new XeDao();
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();

        int offset = (page - 1) * PAGE_SIZE;
        List<NguoiDung> nguoiDungList = nguoiDungDao.getNguoiDungByPage(offset, PAGE_SIZE, loaiNguoiDung);

        long totalNguoiDung = nguoiDungDao.getTotalNguoiDung(loaiNguoiDung);
        int totalPages = (int) Math.ceil((double) totalNguoiDung / PAGE_SIZE);
        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.of(
            "nguoiDungList", nguoiDungList,
            "currentPage", page,
            "totalPages", totalPages,
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai
        );
    }
    
    @GetMapping("/tuyenxe")
    public Map<String, Object> getTuyenXe(
            @RequestParam(value = "page", defaultValue = "1") int page) {

        TuyenXeDao tuyenXeDao = new TuyenXeDao();
        BenXeDao benXeDao = new BenXeDao();
        QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
        XeDao xeDao = new XeDao();
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
        NguoiDungDao nguoiDungDao = new NguoiDungDao();

        int offset = (page - 1) * 4;
        List<TuyenXe> tuyenXeList = tuyenXeDao.getTuyenXeByPage(offset, 4);
        List<BenXe> benXeList = benXeDao.getAllBenXe();
        List<QuanHuyen> quanHuyenList = quanHuyenDao.getAllQuanHuyen();
        
        long totalTuyenXe = tuyenXeDao.getTotalTuyenXe();
        int totalPages = (int) Math.ceil((double) totalTuyenXe / 4);
        
        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.of(
            "tuyenXeList", tuyenXeList,
            "benXeList", benXeList,
            "quanHuyenList", quanHuyenList,
            "currentPage", page,
            "totalPages", totalPages,
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai
        );
    }
    
    @GetMapping("/chuyenxe")
    public Map<String, Object> getChuyenXe(
            @RequestParam(value = "page", defaultValue = "1") int page) {

        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        TuyenXeDao tuyenXeDao = new TuyenXeDao();
        XeDao xeDao = new XeDao();
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();

        int offset = (page - 1) * PAGE_SIZE;
        List<ChuyenXe> chuyenXeList = chuyenXeDao.getChuyenXeByPage(offset, PAGE_SIZE);
        List<TuyenXe> tuyenXeList = tuyenXeDao.getAllTuyenXe();
        List<Xe> xeList = xeDao.getAllXe();
        List<NguoiDung> nguoiDungList = nguoiDungDao.getTaiXe();
        
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        int totalPages = (int) Math.ceil((double) totalChuyenXe / PAGE_SIZE);
        
        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.of(
            "chuyenXeList", chuyenXeList,
            "tuyenXeList", tuyenXeList,
            "xeList", xeList,
            "nguoiDungList", nguoiDungList,
            "currentPage", page,
            "totalPages", totalPages,
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai
        );
    }
    
    @GetMapping("/xe")
    public Map<String, Object> getXe(
            @RequestParam(value = "page", defaultValue = "1") int page) {

        XeDao xeDao = new XeDao();
        LoaiXeDao loaiXeDao = new LoaiXeDao();
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();

        int offset = (page - 1) * PAGE_SIZE;
        List<Xe> xeList = xeDao.getXeByPage(offset, PAGE_SIZE);
        List<LoaiXe> loaiXeList = loaiXeDao.getAllLoaiXe();
        
        long totalXe = xeDao.getTotalXe();
        int totalPages = (int) Math.ceil((double) totalXe / PAGE_SIZE);
        
        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.of(
            "xeList", xeList,
            "loaiXeList", loaiXeList,
            "currentPage", page,
            "totalPages", totalPages,
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai
        );
    }
    
    @GetMapping("/diadiem")
    public Map<String, Object> getDiaDiem(
            @RequestParam(value = "pageQuan", defaultValue = "1") int pageQuan,
            @RequestParam(value = "pageTinh", defaultValue = "1") int pageTinh) {

        QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
        TinhThanhDao tinhThanhDao = new TinhThanhDao();
        XeDao xeDao = new XeDao();
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();

        int offsetQuan = (pageQuan - 1) * PAGE_SIZE;
        List<QuanHuyen> quanHuyenList = quanHuyenDao.getQuanHuyenByPage(offsetQuan, PAGE_SIZE);

        int offsetTinh = (pageTinh - 1) * PAGE_SIZE;
        List<TinhThanh> tinhThanhList = tinhThanhDao.getTinhThanhByPage(offsetTinh, PAGE_SIZE);
        List<TinhThanh> tinhThanhAllList = tinhThanhDao.getAllTinhThanh();

        long totalQuanHuyen = quanHuyenDao.getTotalQuanHuyen();
        int totalQuanPages = (int) Math.ceil((double) totalQuanHuyen / PAGE_SIZE);

        long totalTinhThanh = tinhThanhDao.getTotalTinhThanh();
        int totalTinhPages = (int) Math.ceil((double) totalTinhThanh / PAGE_SIZE);
        
        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.ofEntries(
        	    Map.entry("quanHuyenList", quanHuyenList),
        	    Map.entry("tinhThanhList", tinhThanhList),
        	    Map.entry("currentQuanPage", pageQuan),
        	    Map.entry("totalQuanPages", totalQuanPages),
        	    Map.entry("currentTinhPage", pageTinh),
        	    Map.entry("totalTinhPages", totalTinhPages),
        	    Map.entry("totalCustomer", totalCustomer),
        	    Map.entry("totalXe", totalXe),
        	    Map.entry("totalChuyenXe", totalChuyenXe),
        	    Map.entry("tongDoanhThuThangHienTai", tongDoanhThuThangHienTai),
        	    Map.entry("tinhThanhAllList", tinhThanhAllList)
        );

    }

    @GetMapping("/ticket")
    public Map<String, Object> getAllBookingInfo(
            @RequestParam(value = "page", defaultValue = "1") int page) {
    	
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
        XeDao xeDao = new XeDao();
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        NguoiDungDao nguoiDungDao = new NguoiDungDao();

        int offset = (page - 1) * PAGE_SIZE;

        List<BookingInfo> bookingInfoList = phieuDatVeDao.getBookingInfoByPage(offset, PAGE_SIZE);

        long total = phieuDatVeDao.getTotalBookingInfo();
        int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);
        
        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.of(
            "bookingInfoList", bookingInfoList,
            "currentPage", page,
            "totalPages", totalPages,
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai
        );
    }

    @PutMapping("/nguoidung/xoa/{id}")
    public ResponseEntity<String> xoaNguoiDung(@PathVariable("id") int id) {
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        boolean thanhCong = nguoiDungDao.xoaNguoiDung(id);

        if (thanhCong) {
            return ResponseEntity.ok("Xoá người dùng (mềm) thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng hoặc lỗi trong quá trình xoá");
        }
    }
    
    @PostMapping("/update-tuyenxe")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateTuyenXe(@RequestBody TuyenXeUpdateDTO tuyenXe) {
        Map<String, Object> response = new HashMap<>();
        
        System.out.println("Giá trị tuyến xe in ra###: " + tuyenXe);

        if (tuyenXe == null || tuyenXe.getIdTuyenXe() == 0) {
            response.put("success", false);
            response.put("message", "Thông tin tuyến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            TuyenXeDao tuyenXeDao = new TuyenXeDao();
            TuyenXe existingTuyen = tuyenXeDao.getTuyenXeById(tuyenXe.getIdTuyenXe());

            if (existingTuyen == null) {
                response.put("success", false);
                response.put("message", "Tuyến xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            existingTuyen.setTenTuyen(tuyenXe.getTenTuyen());
            existingTuyen.setQuangDuong(tuyenXe.getQuangDuong());
            existingTuyen.setThoiGianDiChuyenTB(tuyenXe.getThoiGianDiChuyenTB());
            existingTuyen.setSoChuyenTrongNgay(tuyenXe.getSoChuyenTrongNgay());
            existingTuyen.setSoNgayChayTrongTuan(tuyenXe.getSoNgayChayTrongTuan());

            BenXe benXeDi = new BenXe();
            benXeDi.setIdBenXe(tuyenXe.getIdBenXeDi());
            existingTuyen.setBenXeDi(benXeDi);

            BenXe benXeDen = new BenXe();
            benXeDen.setIdBenXe(tuyenXe.getIdBenXeDen());
            existingTuyen.setBenXeDen(benXeDen);

            TinhThanh tinhThanhDi = new TinhThanh();
            tinhThanhDi.setIdTinhThanh(tuyenXe.getIdTinhThanhDi());
            existingTuyen.setTinhThanhDi(tinhThanhDi);

            TinhThanh tinhThanhDen = new TinhThanh();
            tinhThanhDen.setIdTinhThanh(tuyenXe.getIdTinhThanhDen());
            existingTuyen.setTinhThanhDen(tinhThanhDen);

            tuyenXeDao.updateTuyenXe(existingTuyen);

            response.put("success", true);
            response.put("message", "Cập nhật tuyến xe thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/tuyenxe/xoa/{id}")
    public ResponseEntity<String> xoaTuyenXe(@PathVariable("id") int id) {
        TuyenXeDao tuyenXeDao = new TuyenXeDao();
        boolean thanhCong = tuyenXeDao.xoaTuyenXe(id);

        if (thanhCong) {
            return ResponseEntity.ok("Xoá tuyến xe (mềm) thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tuyến xe hoặc lỗi trong quá trình xoá");
        }
    }
    
    @GetMapping("/taikhoan")
    public Map<String, Object> getTaiKhoan() {

        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        XeDao xeDao = new XeDao();
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();

        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.of(
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai
        );
    }
    
    @PostMapping("/update-chuyenxe")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateChuyenXe(@RequestBody ChuyenXeUpdateDTO chuyenXeDto) {
        Map<String, Object> response = new HashMap<>();

        if (chuyenXeDto == null || chuyenXeDto.getIdChuyenXe() == 0) {
            response.put("success", false);
            response.put("message", "Thông tin chuyến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            ChuyenXeDao chuyenXeDao = new ChuyenXeDao();

            ChuyenXe chuyenXe = chuyenXeDao.getChuyenXeById(chuyenXeDto.getIdChuyenXe());

            if (chuyenXe == null) {
                response.put("success", false);
                response.put("message", "Chuyến xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            Date thoiDiemDi = sdf.parse(chuyenXeDto.getThoiDiemDi());
            Date thoiDiemDen = sdf.parse(chuyenXeDto.getThoiDiemDen());
            
            System.out.println("Thời điểm đi sau khi chuyển đổi: " + thoiDiemDi);
            System.out.println("Thời điểm đến sau khi chuyển đổi: " + thoiDiemDen);

            chuyenXe.setThoiDiemDi(thoiDiemDi);
            chuyenXe.setThoiDiemDen(thoiDiemDen);
            chuyenXe.setGiaVe(chuyenXeDto.getGiaVe());
            chuyenXe.setTrangThai(chuyenXeDto.getTrangThai());

            Xe xe = new Xe();
            xe.setIdXe(chuyenXeDto.getIdXe());
            chuyenXe.setXe(xe);

            NguoiDung taiXe = new NguoiDung();
            taiXe.setIdNguoiDung(chuyenXeDto.getIdTaiXe());
            chuyenXe.setTaiXe(taiXe);

            TuyenXe tuyenXe = new TuyenXe();
            tuyenXe.setIdTuyenXe(chuyenXeDto.getIdTuyenXe());
            chuyenXe.setTuyenXe(tuyenXe);

            boolean isUpdated = chuyenXeDao.updateChuyenXe(chuyenXe);

            if (isUpdated) {
                response.put("success", true);
                response.put("message", "Cập nhật chuyến xe thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Cập nhật chuyến xe thất bại!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/chuyenxe/xoa/{id}")
    public ResponseEntity<String> xoaChuyenXe(@PathVariable("id") int id) {
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        boolean thanhCong = chuyenXeDao.xoaChuyenXe(id);

        if (thanhCong) {
            return ResponseEntity.ok("Xoá chuyến xe (mềm) thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chuyến xe hoặc lỗi trong quá trình xoá");
        }
    }
    
    @PostMapping("/update-xe")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateXe(@RequestBody XeUpdateDTO xeDto) {
        Map<String, Object> response = new HashMap<>();

        if (xeDto == null || xeDto.getIdXe() == 0) {
            response.put("success", false);
            response.put("message", "Thông tin xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            XeDao xeDao = new XeDao();
            Xe xe = xeDao.getXeById(xeDto.getIdXe());

            if (xe == null) {
                response.put("success", false);
                response.put("message", "Xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            xe.setTenXe(xeDto.getTenXe());
            xe.setBienSo(xeDto.getBienSo());

            LoaiXe loaiXe = new LoaiXe();
            loaiXe.setIdLoaiXe(xeDto.getIdLoaiXe());
            xe.setLoaiXe(loaiXe);

            boolean isUpdated = xeDao.updateXe(xe);

            if (isUpdated) {
                response.put("success", true);
                response.put("message", "Cập nhật xe thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Cập nhật xe thất bại!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/xe/xoa/{id}")
    public ResponseEntity<String> xoaXe(@PathVariable("id") int id) {
        XeDao xeDao = new XeDao();
        boolean thanhCong = xeDao.xoaXe(id);

        if (thanhCong) {
            return ResponseEntity.ok("Xoá xe (mềm) thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy xe hoặc lỗi trong quá trình xoá");
        }
    }
    
    @PostMapping("/update-quanhuyen")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateQuanHuyen(@RequestBody QuanHuyenUpdateDTO quanHuyen) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("Giá trị quận/huyện nhận được: " + quanHuyen);

        if (quanHuyen == null || quanHuyen.getIdQuanHuyen() == 0) {
            response.put("success", false);
            response.put("message", "Thông tin quận/huyện không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
            QuanHuyen existing = quanHuyenDao.getQuanHuyenById(quanHuyen.getIdQuanHuyen());

            if (existing == null) {
                response.put("success", false);
                response.put("message", "Không tìm thấy quận/huyện để cập nhật!");
                return ResponseEntity.badRequest().body(response);
            }

            existing.setTenQuanHuyen(quanHuyen.getTenQuanHuyen());

            TinhThanh tinh = new TinhThanh();
            tinh.setIdTinhThanh(quanHuyen.getIdTinhThanh());
            existing.setTinhThanh(tinh);

            quanHuyenDao.updateQuanHuyen(existing);

            response.put("success", true);
            response.put("message", "Cập nhật quận/huyện thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/quanhuyen/xoa/{id}")
    public ResponseEntity<String> xoaQuanHuyen(@PathVariable("id") int id) {
        QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
        boolean thanhCong = quanHuyenDao.xoaQuanHuyen(id);

        if (thanhCong) {
            return ResponseEntity.ok("Xoá quận/huyện (mềm) thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy quận/huyện hoặc lỗi trong quá trình xoá");
        }
    }
    
    @PostMapping("/update-tinhthanh")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateTinhThanh(@RequestBody TinhThanh tinhThanhRequest) {
        Map<String, Object> response = new HashMap<>();
        
        System.out.println("Dữ liệu tỉnh/thành nhận được: " + tinhThanhRequest);

        if (tinhThanhRequest == null || tinhThanhRequest.getIdTinhThanh() == 0 || tinhThanhRequest.getTenTinh() == null || tinhThanhRequest.getTenTinh().trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Thông tin tỉnh/thành không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            TinhThanhDao tinhThanhDao = new TinhThanhDao();
            TinhThanh existingTinh = tinhThanhDao.getTinhThanhById(tinhThanhRequest.getIdTinhThanh());

            if (existingTinh == null) {
                response.put("success", false);
                response.put("message", "Tỉnh/Thành không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            existingTinh.setTenTinh(tinhThanhRequest.getTenTinh().trim());
            tinhThanhDao.updateTinhThanh(existingTinh);

            response.put("success", true);
            response.put("message", "Cập nhật tỉnh/thành thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/tinhthanh/xoa/{id}")
    public ResponseEntity<String> xoaTinhThanh(@PathVariable("id") int id) {
        TinhThanhDao tinhThanhDao = new TinhThanhDao();
        boolean thanhCong = tinhThanhDao.xoaTinhThanh(id);

        if (thanhCong) {
            return ResponseEntity.ok("Xoá tỉnh thành (mềm) thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tỉnh thành hoặc lỗi trong quá trình xoá");
        }
    }
    
    @GetMapping("/thongke")
    public Map<String, Object> getThongKe(@RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        XeDao xeDao = new XeDao();
        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();

        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();
        List<String> ngayList = new ArrayList<>();
        List<BigDecimal> tongTienList = new ArrayList<>();
        
        if (startDate != null && endDate != null) {
        	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        	DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        	LocalDate startDateParsed = LocalDate.parse(startDate, inputFormatter);
        	LocalDate endDateParsed = LocalDate.parse(endDate, inputFormatter);

        	String formattedStartDate = startDateParsed.format(outputFormatter);
        	String formattedEndDate = endDateParsed.format(outputFormatter);
        	
        	List<Object[]> doanhThuList = phieuDatVeDao.getDoanhThuTheoNgay(formattedStartDate, formattedEndDate);
        	
        	Map<String, BigDecimal> tongTienTheoNgay = new LinkedHashMap<>(); 

        	for (Object[] row : doanhThuList) {
        	    Date ngay = (Date) row[0];
        	    BigDecimal tongTien = (BigDecimal) row[1];

        	    String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(ngay);
        	    
        	    tongTienTheoNgay.put(formattedDate, tongTienTheoNgay.getOrDefault(formattedDate, BigDecimal.ZERO).add(tongTien));
        	}
        	
        	ngayList = new ArrayList<>(tongTienTheoNgay.keySet());
        	tongTienList = new ArrayList<>(tongTienTheoNgay.values());
        }
        
        System.out.println("ngayList:" + ngayList);
    	System.out.println("tongTienList:" + tongTienList);

        return Map.of(
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai,
            "ngayList", ngayList,
        	"tongTienList", tongTienList
        );
    }
    
    @PutMapping("/update-ve/{id}")
    public ResponseEntity<Map<String, Object>> capNhatTrangThaiVe(
            @PathVariable("id") int id,
            @RequestBody Map<String, Integer> requestBody) {

        Map<String, Object> response = new HashMap<>();

        try {
            int trangThai = requestBody.get("trangThai");

            PhieuDatVeDao dao = new PhieuDatVeDao();
            PhieuDatVe phieu = dao.getPhieuDatVeById(id);

            if (phieu == null) {
                response.put("success", false);
                response.put("message", "Không tìm thấy phiếu đặt vé!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            boolean updated = dao.updatePhieuDatVe(id, trangThai);

            if (updated) {
                response.put("success", true);
                response.put("message", "Cập nhật trạng thái vé thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Cập nhật thất bại!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/cancel-ve/{id}")
    public ResponseEntity<Map<String, Object>> huyVe(
            @PathVariable("id") int id,
            @RequestBody Map<String, Object> requestBody) {

        Map<String, Object> response = new HashMap<>();

        try {
        	int trangThai = (int) requestBody.get("trangThai");
            List<Integer> idGheList = ((List<?>) requestBody.get("idGheList"))
            	    .stream()
            	    .map(obj -> Integer.parseInt(obj.toString()))
            	    .collect(Collectors.toList());
            
            ViTriGheDao viTriGheDao = new ViTriGheDao();
            PhieuDatVeDao dao = new PhieuDatVeDao();
            PhieuDatVe phieu = dao.getPhieuDatVeById(id);

            if (phieu == null) {
                response.put("success", false);
                response.put("message", "Không tìm thấy phiếu đặt vé!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            boolean updated = dao.updatePhieuDatVe(id, trangThai);
            
            for (Integer idGhe : idGheList) {
            	try {
                    viTriGheDao.updateTrangThai(idGhe, 0);
                } catch (Exception e) {
                    System.out.println("Không thể cập nhật id ghế: " + idGhe);
                    e.printStackTrace();
                }
            }

            if (updated) {
                response.put("success", true);
                response.put("message", "Cập nhật trạng thái vé thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Cập nhật thất bại!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/xe/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themXe(@RequestBody Xe xeRequest) {
        Map<String, Object> response = new HashMap<>();

        if (xeRequest == null || xeRequest.getBienSo() == null || xeRequest.getBienSo().trim().isEmpty() ||
            xeRequest.getTenXe() == null || xeRequest.getTenXe().trim().isEmpty() ||
            xeRequest.getLoaiXe() == null || xeRequest.getLoaiXe().getIdLoaiXe() == 0) {

            response.put("success", false);
            response.put("message", "Thông tin xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            XeDao xeDao = new XeDao();
            LoaiXeDao loaiXeDao = new LoaiXeDao();

            xeRequest.setBienSo(xeRequest.getBienSo().trim());
            xeRequest.setTenXe(xeRequest.getTenXe().trim());

            LoaiXe loaiXe = loaiXeDao.getLoaiXeById(xeRequest.getLoaiXe().getIdLoaiXe());
            if (loaiXe == null) {
                response.put("success", false);
                response.put("message", "Loại xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            xeRequest.setLoaiXe(loaiXe);
            xeRequest.setTrangThai(1); 

            Xe xeDaThem = xeDao.themXe(xeRequest);
            
            xeDao.themDanhSachViTriGheTheoXe(xeDaThem, loaiXe.getSoGhe());

            response.put("success", true);
            response.put("message", "Thêm xe thành công!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm xe: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/quanhuyen/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themQuanHuyen(@RequestBody QuanHuyen quanHuyenRequest) {
        Map<String, Object> response = new HashMap<>();

        if (quanHuyenRequest == null ||
            quanHuyenRequest.getTenQuanHuyen() == null || quanHuyenRequest.getTenQuanHuyen().trim().isEmpty() ||
            quanHuyenRequest.getTinhThanh() == null || quanHuyenRequest.getTinhThanh().getIdTinhThanh() == 0) {

            response.put("success", false);
            response.put("message", "Thông tin Quận/Huyện không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
            TinhThanhDao tinhThanhDao = new TinhThanhDao();

            quanHuyenRequest.setTenQuanHuyen(quanHuyenRequest.getTenQuanHuyen().trim());

            TinhThanh tinhThanh = tinhThanhDao.getTinhThanhById(quanHuyenRequest.getTinhThanh().getIdTinhThanh());
            if (tinhThanh == null) {
                response.put("success", false);
                response.put("message", "Tỉnh/Thành không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            quanHuyenRequest.setTinhThanh(tinhThanh);
            quanHuyenRequest.setTrangThai(1); 

            quanHuyenDao.themQuanHuyen(quanHuyenRequest);

            response.put("success", true);
            response.put("message", "Thêm Quận/Huyện thành công!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm Quận/Huyện: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/tinhthanh/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themTinhThanh(@RequestBody TinhThanh tinhThanhRequest) {
        Map<String, Object> response = new HashMap<>();

        if (tinhThanhRequest == null || tinhThanhRequest.getTenTinh() == null || tinhThanhRequest.getTenTinh().trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Tên Tỉnh/Thành không được để trống!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            TinhThanhDao tinhThanhDao = new TinhThanhDao();

            tinhThanhRequest.setTenTinh(tinhThanhRequest.getTenTinh().trim());
            tinhThanhRequest.setTrangThai(1);

            TinhThanh daThem = tinhThanhDao.themTinhThanh(tinhThanhRequest);

            if (daThem != null) {
                response.put("success", true);
                response.put("message", "Thêm Tỉnh/Thành thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Thêm Tỉnh/Thành thất bại!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm Tỉnh/Thành: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/nguoi-dung/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themNguoiDung(@RequestBody NguoiDung nguoiDungRequest) {
        Map<String, Object> response = new HashMap<>();

        if (nguoiDungRequest == null ||
            nguoiDungRequest.getHoTen() == null || nguoiDungRequest.getHoTen().trim().isEmpty() ||
            nguoiDungRequest.getNamSinh() <= 1900 ||
            nguoiDungRequest.getSoDienThoai() == null || nguoiDungRequest.getSoDienThoai().trim().isEmpty() ||
            nguoiDungRequest.getDiaChi() == null || nguoiDungRequest.getDiaChi().trim().isEmpty()) {

            response.put("success", false);
            response.put("message", "Thông tin người dùng không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        int currentYear = Year.now().getValue();
        if (currentYear - nguoiDungRequest.getNamSinh() < 16) {
            response.put("success", false);
            response.put("message", "Người dùng phải từ 16 tuổi trở lên!");
            return ResponseEntity.badRequest().body(response);
        }

        String sdt = nguoiDungRequest.getSoDienThoai().trim();
        if (!sdt.matches("^0\\d{9}$")) {
            response.put("success", false);
            response.put("message", "Số điện thoại phải bắt đầu bằng 0 và có đúng 10 chữ số!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            NguoiDungDao nguoiDungDao = new NguoiDungDao();

            nguoiDungRequest.setTrangThai(1);
            nguoiDungRequest.setIdPhanQuyen(2);
            nguoiDungRequest.setNgayDangKy(new Date());

            boolean success = nguoiDungDao.themNguoiDung(nguoiDungRequest);
            if (success) {
                response.put("success", true);
                response.put("message", "Thêm người dùng thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Không thể thêm người dùng vào cơ sở dữ liệu.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm người dùng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/tuyenxe/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themTuyenXe(@RequestBody TuyenXe tuyenXeRequest) {
        Map<String, Object> response = new HashMap<>();

        if (tuyenXeRequest == null ||
            tuyenXeRequest.getTenTuyen() == null || tuyenXeRequest.getTenTuyen().trim().isEmpty() ||
            tuyenXeRequest.getTinhThanhDi() == null || tuyenXeRequest.getTinhThanhDi().getIdTinhThanh() == 0 ||
            tuyenXeRequest.getTinhThanhDen() == null || tuyenXeRequest.getTinhThanhDen().getIdTinhThanh() == 0 ||
            tuyenXeRequest.getBenXeDi() == null || tuyenXeRequest.getBenXeDi().getIdBenXe() == 0 ||
            tuyenXeRequest.getBenXeDen() == null || tuyenXeRequest.getBenXeDen().getIdBenXe() == 0) {

            response.put("success", false);
            response.put("message", "Thông tin Tuyến Xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            TuyenXeDao tuyenXeDao = new TuyenXeDao();
            TinhThanhDao tinhThanhDao = new TinhThanhDao();
            BenXeDao benXeDao = new BenXeDao();
            float thoiGianDiChuyenTB = tuyenXeRequest.getThoiGianDiChuyenTB();

            tuyenXeRequest.setTenTuyen(tuyenXeRequest.getTenTuyen().trim());
            tuyenXeRequest.setThoiGianDiChuyenTB(thoiGianDiChuyenTB);

            TinhThanh tinhDi = tinhThanhDao.getTinhThanhById(tuyenXeRequest.getTinhThanhDi().getIdTinhThanh());
            TinhThanh tinhDen = tinhThanhDao.getTinhThanhById(tuyenXeRequest.getTinhThanhDen().getIdTinhThanh());

            if (tinhDi == null || tinhDen == null) {
                response.put("success", false);
                response.put("message", "Tỉnh/Thành đi hoặc đến không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            BenXe benDi = benXeDao.getBenXeById(tuyenXeRequest.getBenXeDi().getIdBenXe());
            BenXe benDen = benXeDao.getBenXeById(tuyenXeRequest.getBenXeDen().getIdBenXe());

            if (benDi == null || benDen == null) {
                response.put("success", false);
                response.put("message", "Bến xe đi hoặc đến không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            tuyenXeRequest.setTinhThanhDi(tinhDi);
            tuyenXeRequest.setTinhThanhDen(tinhDen);
            tuyenXeRequest.setBenXeDi(benDi);
            tuyenXeRequest.setBenXeDen(benDen);
            tuyenXeRequest.setTrangThai(1);
            System.out.println("tuyenXeRequest: " + tuyenXeRequest);

            tuyenXeDao.themTuyenXe(tuyenXeRequest);

            response.put("success", true);
            response.put("message", "Thêm tuyến xe thành công!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm tuyến xe: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/chuyenxe/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themChuyenXe(@RequestBody ChuyenXe chuyenXeRequest) {
        Map<String, Object> response = new HashMap<>();

        if (chuyenXeRequest == null ||
            chuyenXeRequest.getThoiDiemDi() == null ||
            chuyenXeRequest.getThoiDiemDen() == null ||
            chuyenXeRequest.getTuyenXe() == null || chuyenXeRequest.getTuyenXe().getIdTuyenXe() == 0 ||
            chuyenXeRequest.getTaiXe() == null || chuyenXeRequest.getTaiXe().getIdNguoiDung() == 0 ||
            chuyenXeRequest.getXe() == null || chuyenXeRequest.getXe().getIdXe() == 0) {

            response.put("success", false);
            response.put("message", "Thông tin chuyến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            TuyenXeDao tuyenXeDao = new TuyenXeDao();
            NguoiDungDao taiKhoanDao = new NguoiDungDao();
            XeDao xeDao = new XeDao();
            ChuyenXeDao chuyenXeDao = new ChuyenXeDao();

            TuyenXe tuyenXe = tuyenXeDao.getTuyenXeById(chuyenXeRequest.getTuyenXe().getIdTuyenXe());
            NguoiDung taiXe = taiKhoanDao.getNguoiDungById(chuyenXeRequest.getTaiXe().getIdNguoiDung());
            Xe xe = xeDao.getXeById(chuyenXeRequest.getXe().getIdXe());

            if (tuyenXe == null || taiXe == null || xe == null) {
                response.put("success", false);
                response.put("message", "Tuyến xe, tài xế hoặc xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            chuyenXeRequest.setTuyenXe(tuyenXe);
            chuyenXeRequest.setTaiXe(taiXe);
            chuyenXeRequest.setXe(xe);
            chuyenXeRequest.setTrangThai(1); 

            chuyenXeDao.themChuyenXe(chuyenXeRequest);

            response.put("success", true);
            response.put("message", "Thêm chuyến xe thành công!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm chuyến xe: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/benxe")
    public Map<String, Object> getBenXe(
            @RequestParam(value = "page", defaultValue = "1") int page) {

        ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
        XeDao xeDao = new XeDao();
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
        BenXeDao benXeDao = new BenXeDao();
        QuanHuyenDao quanHuyenDao = new QuanHuyenDao();

        int offset = (page - 1) * PAGE_SIZE;
        List<BenXe> benXeList = benXeDao.getBenXeByPage(offset, PAGE_SIZE);
        
        long totalBenXe = benXeDao.getTotalBenXe();
        int totalPages = (int) Math.ceil((double) totalBenXe / PAGE_SIZE);
        
        List<QuanHuyen> quanHuyenList = quanHuyenDao.getAllQuanHuyen();
        
        long totalCustomer = nguoiDungDao.getTotalNguoiDung(1);
        long totalXe = xeDao.getTotalXe();
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        BigDecimal tongDoanhThuThangHienTai = phieuDatVeDao.getTongDoanhThuThangHienTai();

        return Map.of(
        	"benXeList", benXeList,
        	"quanHuyenList", quanHuyenList,
        	"currentPage", page,
        	"totalPages", totalPages,
            "totalCustomer", totalCustomer,
            "totalXe", totalXe,
            "totalChuyenXe", totalChuyenXe,
            "tongDoanhThuThangHienTai", tongDoanhThuThangHienTai
        );
    }
    
    @PostMapping("/update-benxe")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateBenXe(@RequestBody BenXe benXeRequest) {
        Map<String, Object> response = new HashMap<>();
        
        System.out.println("Dữ liệu bến xe nhận được: " + benXeRequest);

        if (benXeRequest == null || benXeRequest.getIdBenXe() == 0 || benXeRequest.getTenBenXe() == null ||
            benXeRequest.getDiaChi() == null || benXeRequest.getDiaChi().trim().isEmpty() || benXeRequest.getSoDienThoai() == null || benXeRequest.getSoDienThoai().trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Thông tin bến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            BenXeDao benXeDao = new BenXeDao();
            BenXe existingBenXe = benXeDao.getBenXeById(benXeRequest.getIdBenXe());

            if (existingBenXe == null) {
                response.put("success", false);
                response.put("message", "Bến xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            existingBenXe.setTenBenXe(benXeRequest.getTenBenXe().trim());
            existingBenXe.setDiaChi(benXeRequest.getDiaChi().trim());
            existingBenXe.setSoDienThoai(benXeRequest.getSoDienThoai().trim());
            existingBenXe.setQuanHuyen(benXeRequest.getQuanHuyen());

            benXeDao.updateBenXe(existingBenXe);

            response.put("success", true);
            response.put("message", "Cập nhật bến xe thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/benxe/xoa/{id}")
    public ResponseEntity<String> xoaBenXe(@PathVariable("id") int id) {
        BenXeDao benXeDao = new BenXeDao();
        boolean thanhCong = benXeDao.xoaBenXe(id);

        if (thanhCong) {
            return ResponseEntity.ok("Xoá bến xe (mềm) thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bến xe hoặc lỗi trong quá trình xoá");
        }
    }
    
    @PostMapping("/benxe/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themBenXe(@RequestBody BenXe benXeRequest) {
        Map<String, Object> response = new HashMap<>();

        if (benXeRequest == null ||
            benXeRequest.getTenBenXe() == null || benXeRequest.getTenBenXe().trim().isEmpty() ||
            benXeRequest.getDiaChi() == null || benXeRequest.getDiaChi().trim().isEmpty() ||
            benXeRequest.getSoDienThoai() == null || benXeRequest.getSoDienThoai().trim().isEmpty() ||
            benXeRequest.getQuanHuyen() == null || benXeRequest.getQuanHuyen().getIdQuanHuyen() == 0) {

            response.put("success", false);
            response.put("message", "Thông tin bến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
            BenXeDao benXeDao = new BenXeDao();

            QuanHuyen quanHuyen = quanHuyenDao.getQuanHuyenById(benXeRequest.getQuanHuyen().getIdQuanHuyen());

            if (quanHuyen == null) {
                response.put("success", false);
                response.put("message", "Quận/Huyện không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            benXeRequest.setQuanHuyen(quanHuyen);
            benXeRequest.setTrangThai(1);

            benXeDao.themBenXe(benXeRequest);

            response.put("success", true);
            response.put("message", "Thêm bến xe thành công!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm bến xe: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/nguoidung/all")
    public List<NguoiDung> getAllNguoiDung() {
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        return nguoiDungDao.getAllNguoiDung();
    }
    
    @GetMapping("/tuyenxe/all")
    public List<TuyenXe> getAllTuyenXe() {
        TuyenXeDao tuyenXeDao = new TuyenXeDao();
        return tuyenXeDao.getAllTuyenXe();
    }
    
    @GetMapping("/phieudatve/all")
    public List<BookingInfo> getAllPhieuDatVe() {
        PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
        return phieuDatVeDao.getAllPhieuDatVe();
    }
    
    @GetMapping("/benxe/all")
    public List<BenXe> getAllBenXe() {
        BenXeDao benXeDao = new BenXeDao();
        return benXeDao.getAllBenXe();
    }
    
    @GetMapping("/benxedto/all")
    public List<BenXeDTO> getAllBenXeDTO() {
        BenXeDao benXeDao = new BenXeDao();
        return benXeDao.getAllBenXeDTO();
    }
    
    @GetMapping("/tinhthanh/all")
    public List<TinhThanh> getAllTinhThanh() {
        TinhThanhDao tinhThanhDao = new TinhThanhDao();
        return tinhThanhDao.getAllTinhThanh();
    }
    
    @GetMapping("/quanhuyen/all")
    public List<QuanHuyen> getAllQuanHuyen() {
    	QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
        return quanHuyenDao.getAllQuanHuyen();
    }
    
    @GetMapping("/getListTuyenXe")
    public List<TuyenXe> getDanhSachTuyenXe(){
        return new TuyenXeDao().getAllTuyenXe();
    }
    @GetMapping("/getListChuyenXe")
    public List<ChuyenXe> getDanhSachChuyenXe(){
        return new ChuyenXeDao().getAllChuyenXe();
    }
    
    @GetMapping("/xe/all")
    public List<Xe> getAllXe() {   
        return new XeDao().getAllXe();
    }
    @GetMapping("/nguoidung/tx")
    public List<NguoiDung> getAllTaiXe() {
        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        return nguoiDungDao.getAllTaiXe();
    }
    
   

    @PutMapping("/chuyenxe/update/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateChuyen(
            @PathVariable("id") int idChuyenXe,
            @RequestBody ChuyenXe chuyenXeRequest
    ) {
        Map<String, Object> response = new HashMap<>();

        // Kiểm tra dữ liệu nhập
        if (chuyenXeRequest == null ||
            chuyenXeRequest.getXe() == null ||
            chuyenXeRequest.getThoiDiemDi() == null || chuyenXeRequest.getThoiDiemDen() == null ||
            chuyenXeRequest.getTuyenXe() == null || chuyenXeRequest.getTaiXe() == null ||
            chuyenXeRequest.getGiaVe() <= 0) {

            response.put("success", false);
            response.put("message", "Thông tin chuyến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
            ChuyenXe existingChuyenXe = chuyenXeDao.getChuyenXeById(idChuyenXe);

            if (existingChuyenXe == null) {
                response.put("success", false);
                response.put("message", "Chuyến xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            //  kiểm tra tài xế có trùng giờ chạy với chuyến khác
            int taiXeId = chuyenXeRequest.getTaiXe().getIdNguoiDung();  
            int idXe = chuyenXeRequest.getXe().getIdXe();
            Date newStart = chuyenXeRequest.getThoiDiemDi();
            Date newEnd = chuyenXeRequest.getThoiDiemDen();

            boolean isTaiXeTrungGio = chuyenXeDao.isTaiXeTrungGioChay(taiXeId, newStart, newEnd, idChuyenXe) ;
            boolean isXeTrungGio = chuyenXeDao.isXeTrungGioChay(idXe, newStart, newEnd, idChuyenXe);
            if (isTaiXeTrungGio || isXeTrungGio ) {
                response.put("success", false);
                response.put("message", "Tài xế hoặc xe đã có chuyến khác chạy trong khoảng thời gian này!");
                return ResponseEntity.badRequest().body(response);
            }

            // Cập nhật dữ liệu
            existingChuyenXe.setThoiDiemDi(newStart);
            existingChuyenXe.setThoiDiemDen(newEnd);
            existingChuyenXe.setGiaVe(chuyenXeRequest.getGiaVe());
            existingChuyenXe.setTrangThai(chuyenXeRequest.getTrangThai());
            existingChuyenXe.setTuyenXe(chuyenXeRequest.getTuyenXe());
            existingChuyenXe.setXe(chuyenXeRequest.getXe());
            existingChuyenXe.setTaiXe(chuyenXeRequest.getTaiXe());

            chuyenXeDao.updateChuyenXe(existingChuyenXe);

            response.put("success", true);
            response.put("message", "Cập nhật chuyến xe thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/chuyen/them")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> themChuyen(@RequestBody ChuyenXe chuyenXeRequest) {
        Map<String, Object> response = new HashMap<>();

        if (chuyenXeRequest == null ||
            chuyenXeRequest.getThoiDiemDi() == null ||
            chuyenXeRequest.getThoiDiemDen() == null ||
            chuyenXeRequest.getTuyenXe() == null || chuyenXeRequest.getTuyenXe().getIdTuyenXe() == 0 ||
            chuyenXeRequest.getTaiXe() == null || chuyenXeRequest.getTaiXe().getIdNguoiDung() == 0 ||
            chuyenXeRequest.getXe() == null || chuyenXeRequest.getXe().getIdXe() == 0) {

            response.put("success", false);
            response.put("message", "Thông tin chuyến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            TuyenXeDao tuyenXeDao = new TuyenXeDao();
            NguoiDungDao taiKhoanDao = new NguoiDungDao();
            XeDao xeDao = new XeDao();
            ChuyenXeDao chuyenXeDao = new ChuyenXeDao();

            TuyenXe tuyenXe = tuyenXeDao.getTuyenXeById(chuyenXeRequest.getTuyenXe().getIdTuyenXe());
            NguoiDung taiXe = taiKhoanDao.getNguoiDungById(chuyenXeRequest.getTaiXe().getIdNguoiDung());
            Xe xe = xeDao.getXeById(chuyenXeRequest.getXe().getIdXe());

            if (tuyenXe == null || taiXe == null || xe == null) {
                response.put("success", false);
                response.put("message", "Tuyến xe, tài xế hoặc xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }
            // kiểm tra tài xế có trùng giờ chạy với chuyến khác
            int taiXeId = chuyenXeRequest.getTaiXe().getIdNguoiDung();  
            int idXe = chuyenXeRequest.getXe().getIdXe();
            Date newStart = chuyenXeRequest.getThoiDiemDi();
            Date newEnd = chuyenXeRequest.getThoiDiemDen();

            boolean isTaiXeTrungGio = chuyenXeDao.isTaiXeTrungGioChay(taiXeId, newStart, newEnd, chuyenXeRequest.getIdChuyenXe()) ;
            boolean isXeTrungGio = chuyenXeDao.isXeTrungGioChay(idXe, newStart, newEnd, chuyenXeRequest.getIdChuyenXe());
            if (isTaiXeTrungGio || isXeTrungGio ) {
                response.put("success", false);
                response.put("message", "Tài xế hoặc xe đã có chuyến khác chạy trong khoảng thời gian này!");
                return ResponseEntity.badRequest().body(response);
            }
            chuyenXeRequest.setTuyenXe(tuyenXe);
            chuyenXeRequest.setTaiXe(taiXe);
            chuyenXeRequest.setXe(xe);
            chuyenXeRequest.setTrangThai(1); 

            chuyenXeDao.themChuyenXe(chuyenXeRequest);

            response.put("success", true);
            response.put("message", "Thêm chuyến xe thành công!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi thêm chuyến xe: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PutMapping("/benxe/update/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateBenXe(
            @PathVariable("id") int idBenXe,
            @RequestBody BenXe benxeRequest
    ) {
        Map<String, Object> response = new HashMap<>();

        // Kiểm tra dữ liệu nhập
        if (benxeRequest == null ||
        	benxeRequest.getTenBenXe() == null || benxeRequest.getDiaChi() == null || benxeRequest.getSoDienThoai() == null ||
        	benxeRequest.getQuanHuyen() == null 	) {

            response.put("success", false);
            response.put("message", "Thông tin bến xe không hợp lệ!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            BenXeDao benXeDao = new BenXeDao();
            BenXe existingBenXe = benXeDao.getBenXeById(idBenXe);

            if (existingBenXe == null) {
                response.put("success", false);
                response.put("message", "Bến xe không tồn tại!");
                return ResponseEntity.badRequest().body(response);
            }

            
            existingBenXe.setTenBenXe(benxeRequest.getTenBenXe());
            existingBenXe.setDiaChi(benxeRequest.getDiaChi());
            existingBenXe.setSoDienThoai(benxeRequest.getSoDienThoai());
            existingBenXe.setQuanHuyen(benxeRequest.getQuanHuyen());
            existingBenXe.setTrangThai(benxeRequest.getTrangThai());
            

            benXeDao.updateBenXe(existingBenXe);

            response.put("success", true);
            response.put("message", "Cập nhật Bến xe thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/quanhuyenbytinh")
    public ResponseEntity<List<QuanHuyen>> getQuanHuyenByTinh(@RequestParam("idTinhThanh") int idTinhThanh) {
        List<QuanHuyen> danhSach = new QuanHuyenDao().getAllQuanHuyenByTinh(idTinhThanh);
        return ResponseEntity.ok(danhSach);
    }
    
    @GetMapping("/loaixe/all")
    public List<LoaiXe> getAllLoaiXe() {   
        return new LoaiXeDao().getAllLoaiXe();
    }
}
