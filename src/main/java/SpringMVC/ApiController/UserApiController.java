package SpringMVC.ApiController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "http://localhost:8086")
@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	@GetMapping("/tinhthanh")
    public Map<String, Object> getTinhThanh() {

		TinhThanhDao tinhThanhDao = new TinhThanhDao();
        List<TinhThanh> tinhThanhList = tinhThanhDao.getAllTinhThanh();

        return Map.of(
            "tinhThanhList", tinhThanhList
        );
    }
	
	@GetMapping("/trip-selection")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> tripSelectionPage(
	        @RequestParam String departure,
	        @RequestParam String destination,
	        @RequestParam String departureDate,
	        @RequestParam(required = false) String returnDate,
	        @RequestParam int tickets) {

	    System.out.println("✅ Nhận dữ liệu từ URL:");
	    System.out.println("🚏 Điểm đi: " + departure);
	    System.out.println("🏁 Điểm đến: " + destination);
	    System.out.println("📅 Ngày đi: " + departureDate);
	    System.out.println("🔄 Ngày về: " + (returnDate != null ? returnDate : "Không có"));
	    System.out.println("🎟️ Số vé: " + tickets);

	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("departure", departure);
	    response.put("destination", destination);
	    response.put("departureDate", departureDate);
	    response.put("returnDate", returnDate);
	    response.put("tickets", tickets);

	    return ResponseEntity.ok(response);
	}

}