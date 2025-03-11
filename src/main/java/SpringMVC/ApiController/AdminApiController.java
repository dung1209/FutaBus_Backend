package SpringMVC.ApiController;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Dao.BenXeDao;
import Dao.ChuyenXeDao;
import Dao.LoaiXeDao;
import Dao.NguoiDungDao;
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
import FutaBus.bean.Xe;
import FutaBus.bean.LoaiXe;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private static final int PAGE_SIZE = 5;

    @GetMapping("/nguoidung")
    public Map<String, Object> getNguoiDung(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "loaiNguoiDung", defaultValue = "1") int loaiNguoiDung) {

        NguoiDungDao nguoiDungDao = new NguoiDungDao();

        int offset = (page - 1) * PAGE_SIZE;
        List<NguoiDung> nguoiDungList = nguoiDungDao.getNguoiDungByPage(offset, PAGE_SIZE, loaiNguoiDung);

        long totalNguoiDung = nguoiDungDao.getTotalNguoiDung(loaiNguoiDung);
        int totalPages = (int) Math.ceil((double) totalNguoiDung / PAGE_SIZE);

        return Map.of(
            "nguoiDungList", nguoiDungList,
            "currentPage", page,
            "totalPages", totalPages
        );
    }
    
    @GetMapping("/tuyenxe")
    public Map<String, Object> getTuyenXe(
            @RequestParam(value = "page", defaultValue = "1") int page) {

        TuyenXeDao tuyenXeDao = new TuyenXeDao();
        BenXeDao benXeDao = new BenXeDao();

        int offset = (page - 1) * 4;
        List<TuyenXe> tuyenXeList = tuyenXeDao.getTuyenXeByPage(offset, 4);
        List<BenXe> benXeList = benXeDao.getAllBenXe();
        
        long totalTuyenXe = tuyenXeDao.getTotalTuyenXe();
        int totalPages = (int) Math.ceil((double) totalTuyenXe / 4);

        return Map.of(
            "tuyenXeList", tuyenXeList,
            "benXeList", benXeList,
            "currentPage", page,
            "totalPages", totalPages
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

    
}
