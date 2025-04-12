package SpringMVC.ApiController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import FutaBus.bean.BookingRequest;
import FutaBus.bean.BookingWrapper;
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

		return Map.of("tinhThanhList", tinhThanhList);
	}

	@GetMapping("/trip-selection")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> tripSelectionPage(@RequestParam int departureId,
			@RequestParam String departure, @RequestParam int destinationId, @RequestParam String destination,
			@RequestParam String departureDate, @RequestParam(required = false) String returnDate,
			@RequestParam int tickets) {

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate date = LocalDate.parse(departureDate, inputFormatter);
		String formattedDate = date.format(outputFormatter);

		ChuyenXeDao chuyenXeDao = new ChuyenXeDao();
		List<ChuyenXeResult> chuyenXeResultList = chuyenXeDao.getChuyenXe(departureId, destinationId, formattedDate,
				tickets);
		int numberOfTrips = chuyenXeResultList.size();

		List<ChuyenXeResult> chuyenXeReturnList = new ArrayList<>();
		int numberOfTripReturns = chuyenXeReturnList.size();

		if (returnDate != null && !returnDate.trim().isEmpty()) {
			LocalDate dateReturn = LocalDate.parse(returnDate, inputFormatter);
			String formattedDateReturn = dateReturn.format(outputFormatter);
			chuyenXeReturnList = chuyenXeDao.getChuyenXe(destinationId, departureId, formattedDateReturn, tickets);
			numberOfTripReturns = chuyenXeReturnList.size();
		}

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
		response.put("numberOfTripReturns", numberOfTripReturns);
		response.put("chuyenXeResultList", chuyenXeResultList);
		response.put("chuyenXeReturnList", chuyenXeReturnList);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/book-tickets")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> bookTicketsPage(@RequestParam String departureId,
			@RequestParam String departure, @RequestParam String destinationId, @RequestParam String destination,
			@RequestParam String start, @RequestParam String end, @RequestParam String departureDate,
			@RequestParam(required = false) String returnDate, @RequestParam String idTrip,
			@RequestParam String startTime, @RequestParam String endTime, @RequestParam String loai,
			@RequestParam String price, @RequestParam String soGhe, @RequestParam String idXe,
			@RequestParam(required = false) String idTripReturn, @RequestParam(required = false) String startTimeReturn,
			@RequestParam(required = false) String endTimeReturn, @RequestParam(required = false) String priceReturn,
			@RequestParam(required = false) String soGheReturn, @RequestParam(required = false) String idXeReturn) {

		ViTriGheDao viTriGheDao = new ViTriGheDao();

		int idXeInt = parseIntSafe(idXe);
		List<ViTriGhe> viTriGheTangDuoiGo = viTriGheDao.getViTriGheTangDuoiByIdXe(idXeInt);
		List<ViTriGhe> viTriGheTangTrenGo = viTriGheDao.getViTriGheTangTrenByIdXe(idXeInt);

		List<ViTriGhe> viTriGheTangDuoiReturn = new ArrayList<>();
		List<ViTriGhe> viTriGheTangTrenReturn = new ArrayList<>();

		if (returnDate != null && !returnDate.trim().isEmpty() && idXeReturn != null) {
			int idXeReturnInt = parseIntSafe(idXeReturn);
			viTriGheTangDuoiReturn = viTriGheDao.getViTriGheTangDuoiByIdXe(idXeReturnInt);
			viTriGheTangTrenReturn = viTriGheDao.getViTriGheTangTrenByIdXe(idXeReturnInt);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("status", "success");

		response.put("departureId", departureId);
		response.put("departure", departure);
		response.put("destinationId", destinationId);
		response.put("destination", destination);
		response.put("departureDate", departureDate);
		response.put("start", start);
		response.put("end", end);
		response.put("idTrip", idTrip);
		response.put("startTime", startTime);
		response.put("endTime", endTime);
		response.put("loai", loai);
		response.put("price", price);
		response.put("soGhe", soGhe);
		response.put("idXe", idXe);
		response.put("viTriGheTangDuoiList", viTriGheTangDuoiGo);
		response.put("viTriGheTangTrenList", viTriGheTangTrenGo);

		if (returnDate != null && !returnDate.trim().isEmpty()) {
			response.put("returnDate", returnDate);
			response.put("idTripReturn", idTripReturn);
			response.put("startTimeReturn", startTimeReturn);
			response.put("endTimeReturn", endTimeReturn);
			response.put("priceReturn", priceReturn);
			response.put("soGheReturn", soGheReturn);
			response.put("idXeReturn", idXeReturn);
			response.put("viTriGheTangDuoiReturnList", viTriGheTangDuoiReturn);
			response.put("viTriGheTangTrenReturnList", viTriGheTangTrenReturn);
		}

		return ResponseEntity.ok(response);
	}

	private int parseIntSafe(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}

	@GetMapping("/checkout")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> checkoutPage(@RequestParam int selectedSeatsCount,
			@RequestParam(required = false) Integer selectedSeatsCountReturn, @RequestParam double totalPrice,
			@RequestParam String nameValue, @RequestParam String phoneValue, @RequestParam String emailValue,
			@RequestParam String selectedSeats, @RequestParam String selectedSeatIds,
			@RequestParam(required = false) String selectedSeatsReturn,
			@RequestParam(required = false) String selectedSeatIdsReturn, @RequestParam Long idTrip,
			@RequestParam String formattedStartTime, @RequestParam String weekday, @RequestParam int departureId,
			@RequestParam String departure, @RequestParam int destinationId, @RequestParam String destination,
			@RequestParam String start, @RequestParam String end, @RequestParam String departureDate,
			@RequestParam String returnDate, @RequestParam String startTime, @RequestParam String endTime,
			@RequestParam String loai, @RequestParam double price, @RequestParam int soGhe, @RequestParam Long idXe,
			@RequestParam(required = false) Double totalPriceReturn, @RequestParam(required = false) Long idTripReturn,
			@RequestParam(required = false) String startTimeReturn,
			@RequestParam(required = false) String endTimeReturn,
			@RequestParam(required = false) String formattedStartTimeReturn,
			@RequestParam(required = false) Double priceReturn, @RequestParam(required = false) Integer soGheReturn,
			@RequestParam(required = false) Long idXeReturn) {

		Map<String, Object> response = new HashMap<>();
		response.put("status", "success");
		response.put("selectedSeatsCount", selectedSeatsCount);
		response.put("selectedSeatsCountReturn", selectedSeatsCountReturn);
		response.put("totalPrice", totalPrice);
		response.put("nameValue", nameValue);
		response.put("phoneValue", phoneValue);
		response.put("emailValue", emailValue);
		response.put("selectedSeats", selectedSeats);
		response.put("selectedSeatIds", selectedSeatIds);
		response.put("selectedSeatsReturn", selectedSeatsReturn);
		response.put("selectedSeatIdsReturn", selectedSeatIdsReturn);
		response.put("idTrip", idTrip);
		response.put("formattedStartTime", formattedStartTime);
		response.put("weekday", weekday);
		response.put("departureId", departureId);
		response.put("departure", departure);
		response.put("destinationId", destinationId);
		response.put("destination", destination);
		response.put("start", start);
		response.put("end", end);
		response.put("departureDate", departureDate);
		response.put("returnDate", returnDate);
		response.put("startTime", startTime);
		response.put("endTime", endTime);
		response.put("loai", loai);
		response.put("price", price);
		response.put("soGhe", soGhe);
		response.put("idXe", idXe);
		response.put("totalPriceReturn", totalPriceReturn);
		response.put("idTripReturn", idTripReturn);
		response.put("startTimeReturn", startTimeReturn);
		response.put("endTimeReturn", endTimeReturn);
		response.put("formattedStartTimeReturn", formattedStartTimeReturn);
		response.put("priceReturn", priceReturn);
		response.put("soGheReturn", soGheReturn);
		response.put("idXeReturn", idXeReturn);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/confirmBooking")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> confirmBooking(@RequestBody BookingWrapper wrapper) {

		BookingRequest go = wrapper.getBookingData();
		BookingRequest back = wrapper.getBookingDataReturn();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		String idViTriGheStr = go.getIdViTriGhe();

		List<Integer> gheList = Arrays.stream(idViTriGheStr.split(",")).map(Integer::parseInt)
				.collect(Collectors.toList());

		PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
		phieuDatVeDao.saveBooking(go);

		if (back != null && back.getIdViTriGhe() != null && !back.getIdViTriGhe().isEmpty()) {
			String idViTriGheReturnStr = back.getIdViTriGhe();
			List<Integer> gheReturnList = Arrays.stream(idViTriGheReturnStr.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			PhieuDatVeDao phieuDatVeReturnDao = new PhieuDatVeDao();
			phieuDatVeReturnDao.saveBooking(back);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("status", "success");
		response.put("message", "Đặt vé thành công!");

		return ResponseEntity.ok(response);
	}
}