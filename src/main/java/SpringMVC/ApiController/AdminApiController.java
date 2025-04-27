package SpringMVC.ApiController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import Dao.XeDao;
import FutaBus.bean.BenXe;
import FutaBus.bean.ChuyenXe;
import FutaBus.bean.NguoiDung;
import FutaBus.bean.QuanHuyen;
import FutaBus.bean.TinhThanh;
import FutaBus.bean.TuyenXe;
import FutaBus.bean.TuyenXeUpdateDTO;
import FutaBus.bean.Xe;
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

        int offset = (page - 1) * 4;
        List<ChuyenXe> chuyenXeList = chuyenXeDao.getChuyenXeByPage(offset, 4);
        List<TuyenXe> tuyenXeList = tuyenXeDao.getTuyenXeByPage(offset, 4);
        List<Xe> xeList = xeDao.getXeByPage(offset, 4);
        List<NguoiDung> nguoiDungList = nguoiDungDao.getNguoiDungByPage(offset, 4, 1);
        
        long totalChuyenXe = chuyenXeDao.getTotalChuyenXe();
        int totalPages = (int) Math.ceil((double) totalChuyenXe / 4);

        return Map.of(
            "chuyenXeList", chuyenXeList,
            "tuyenXeList", tuyenXeList,
            "xeList", xeList,
            "nguoiDungList", nguoiDungList,
            "currentPage", page,
            "totalPages", totalPages
        );
    }
    
    @GetMapping("/xe")
    public Map<String, Object> getXe(
            @RequestParam(value = "page", defaultValue = "1") int page) {

        XeDao xeDao = new XeDao();
        LoaiXeDao loaiXeDao = new LoaiXeDao();

        int offset = (page - 1) * PAGE_SIZE;
        List<Xe> xeList = xeDao.getXeByPage(offset, PAGE_SIZE);
        List<LoaiXe> loaiXeList = loaiXeDao.getAllLoaiXe();
        
        long totalXe = xeDao.getTotalXe();
        int totalPages = (int) Math.ceil((double) totalXe / PAGE_SIZE);

        return Map.of(
            "xeList", xeList,
            "loaiXeList", loaiXeList,
            "currentPage", page,
            "totalPages", totalPages
        );
    }
    
    @GetMapping("/diadiem")
    public Map<String, Object> getDiaDiem(
            @RequestParam(value = "pageQuan", defaultValue = "1") int pageQuan,
            @RequestParam(value = "pageTinh", defaultValue = "1") int pageTinh) {

        QuanHuyenDao quanHuyenDao = new QuanHuyenDao();
        TinhThanhDao tinhThanhDao = new TinhThanhDao();

        int offsetQuan = (pageQuan - 1) * PAGE_SIZE;
        List<QuanHuyen> quanHuyenList = quanHuyenDao.getQuanHuyenByPage(offsetQuan, PAGE_SIZE);

        int offsetTinh = (pageTinh - 1) * PAGE_SIZE;
        List<TinhThanh> tinhThanhList = tinhThanhDao.getTinhThanhByPage(offsetTinh, PAGE_SIZE);

        long totalQuanHuyen = quanHuyenDao.getTotalQuanHuyen();
        int totalQuanPages = (int) Math.ceil((double) totalQuanHuyen / PAGE_SIZE);

        long totalTinhThanh = tinhThanhDao.getTotalTinhThanh();
        int totalTinhPages = (int) Math.ceil((double) totalTinhThanh / PAGE_SIZE);

        return Map.of(
            "quanHuyenList", quanHuyenList,
            "tinhThanhList", tinhThanhList,
            "currentQuanPage", pageQuan,
            "totalQuanPages", totalQuanPages,
            "currentTinhPage", pageTinh,
            "totalTinhPages", totalTinhPages
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

}
