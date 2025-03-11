package SpringMVC.ApiController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/trip-selection")
	public ResponseEntity<Map<String, Object>> bookTrip(@RequestBody Map<String, Object> requestData,
	        HttpSession session) {
	    String departure = (String) requestData.get("departure");
	    String destination = (String) requestData.get("destination");
	    String departureDate = (String) requestData.get("departureDate");
	    String returnDate = (String) requestData.get("returnDate"); 
	    Integer tickets = (Integer) requestData.get("tickets");

	    if (departure == null || destination == null || departureDate == null || tickets == null || tickets <= 0) {
	        return ResponseEntity.badRequest().body(Map.of(
	            "status", "error",
	            "message", "Thiếu thông tin đặt vé!"
	        ));
	    }

	    session.setAttribute("departure", departure);
	    session.setAttribute("destination", destination);
	    session.setAttribute("departureDate", departureDate);
	    session.setAttribute("returnDate", returnDate);
	    session.setAttribute("tickets", tickets);

	    return ResponseEntity.ok(Map.of("status", "success"));
	}
	
}