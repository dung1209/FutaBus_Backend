package SpringMVC.ApiController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import Dao.ViTriGheDao;
import Dao.XeDao;
import FutaBus.bean.BenXe;
import FutaBus.bean.ChuyenXe;
import FutaBus.bean.ChuyenXeResult;
import FutaBus.bean.NguoiDung;
import FutaBus.bean.QuanHuyen;
import FutaBus.bean.TinhThanh;
import FutaBus.bean.TuyenXe;
import FutaBus.bean.ViTriGhe;
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
			@RequestParam int departureId,
	        @RequestParam String departure,
	        @RequestParam int destinationId,
	        @RequestParam String destination,
	        @RequestParam String departureDate,
	        @RequestParam(required = false) String returnDate,
	        @RequestParam int tickets) {
	    
	    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(departureDate, inputFormatter);
        String formattedDate = date.format(outputFormatter);
	    
	    ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
	    List<ChuyenXeResult> chuyenXeResultList = chuyenXeDao.getChuyenXe(departureId, destinationId, formattedDate, tickets);
	    int numberOfTrips = chuyenXeResultList.size();

	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("departureId", departureId);
	    response.put("departure", departure);
	    response.put("destinationId", destinationId);
	    response.put("destination", destination);
	    response.put("departureDate", departureDate);
	    response.put("returnDate", returnDate);
	    response.put("tickets", tickets);
	    response.put("numberOfTrips", numberOfTrips);
	    response.put("chuyenXeResultList", chuyenXeResultList);

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/book-tickets")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> bookTicketsPage(
	        @RequestParam int departureId,
	        @RequestParam String departure,
	        @RequestParam int destinationId,
	        @RequestParam String destination,
	        @RequestParam String start,
	        @RequestParam String end,
	        @RequestParam String departureDate,
	        @RequestParam(required = false) String returnDate, 
	        @RequestParam String startTime,
	        @RequestParam String endTime,
	        @RequestParam String loai,
	        @RequestParam String price,
	        @RequestParam int soGhe,
	        @RequestParam int idXe) {
	    
	    ViTriGheDao viTriGheDao = new ViTriGheDao();
	    List<ViTriGhe> viTriGheTangDuoiList = viTriGheDao.getViTriGheTangDuoiByIdXe(idXe);
	    List<ViTriGhe> viTriGheTangTrenList = viTriGheDao.getViTriGheTangTrenByIdXe(idXe);
	    
	    if (viTriGheTangDuoiList == null) {
	        viTriGheTangDuoiList = new ArrayList<>();
	    }

	    if (viTriGheTangTrenList == null) {
	        viTriGheTangTrenList = new ArrayList<>();
	    }


	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("departureId", departureId);
	    response.put("departure", departure);
	    response.put("destinationId", destinationId);
	    response.put("destination", destination);
	    response.put("departureDate", departureDate);
	    response.put("returnDate", returnDate);
	    response.put("start", start);
	    response.put("end", end);
	    response.put("startTime", startTime);
	    response.put("endTime", endTime);
	    response.put("loai", loai);
	    response.put("price", price);
	    response.put("soGhe", soGhe);
	    response.put("idXe", idXe);
	    response.put("viTriGheTangDuoiList", viTriGheTangDuoiList);
	    response.put("viTriGheTangTrenList", viTriGheTangTrenList);
	    return ResponseEntity.ok(response);
	}

}