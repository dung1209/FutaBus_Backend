package SpringMVC.ApiController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import FutaBus.bean.OtpRequest;
import FutaBus.bean.QuanHuyen;
import FutaBus.bean.TinhThanh;
import FutaBus.bean.TuyenXe;
import FutaBus.bean.VerifyOtpRequest;
import FutaBus.bean.ViTriGhe;
import FutaBus.bean.Xe;
import FutaBus.bean.LoaiXe;
import FutaBus.bean.LoginRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserApiController {

	@Autowired
    private JavaMailSender mailSender;

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
			@RequestParam(required = false) String returnDate, @RequestParam String startTime, @RequestParam String endTime,
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
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
	    System.out.println("Nhận yêu cầu đăng nhập từ client:");
	    System.out.println("email: " + loginRequest.getEmail());
	    System.out.println("matKhau: " + loginRequest.getMatKhau());

	    NguoiDungDao nguoiDungDao = new NguoiDungDao();
	    NguoiDung nguoiDung = null;
	    Map<String, Object> response = new HashMap<>();

	    try {
	        System.out.println("Bắt đầu kiểm tra đăng nhập...");
	        nguoiDung = nguoiDungDao.checkLogin(loginRequest.getEmail(), loginRequest.getMatKhau());
	        System.out.println("Kiểm tra đăng nhập hoàn tất!");
	    } catch (Exception e) {
	        System.out.println("Lỗi khi kiểm tra đăng nhập: " + e.getMessage());
	        response.put("status", "error");
	        response.put("message", "Lỗi server: " + e.getMessage());
	        return ResponseEntity.status(500).body(response);
	    }

	    if (nguoiDung != null) {
	        System.out.println("Đăng nhập thành công!");
	        System.out.println("idNguoiDung: " + nguoiDung.getIdNguoiDung());
	        System.out.println("idPhanQuyen: " + nguoiDung.getIdPhanQuyen());

	        session.setAttribute("currentUser", nguoiDung);

	        response.put("status", "success");
	        response.put("message", "Đăng nhập thành công!");
	        response.put("nguoiDung", Map.of(
	            "idNguoiDung", nguoiDung.getIdNguoiDung(),
	            "idPhanQuyen", nguoiDung.getIdPhanQuyen()
	        ));
	    } else {
	        System.out.println("Đăng nhập thất bại - sai thông tin!");
	        response.put("status", "error");
	        response.put("message", "Sai tên đăng nhập hoặc mật khẩu");
	    }

	    return ResponseEntity.ok(response);
	}

	 
	
	@PostMapping("/send-otp")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> sendOtp(@RequestBody OtpRequest otpRequest, HttpSession session) {
        String email = otpRequest.getEmail();
        Map<String, Object> response = new HashMap<>();

        if (email == null || email.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Email không được để trống");
            return ResponseEntity.badRequest().body(response);
        }

        NguoiDungDao nguoiDungDao = new NguoiDungDao();
        if (nguoiDungDao.checkEmailExists(email)) {
            response.put("success", false);
            response.put("message", "Email đã được đăng ký");
            return ResponseEntity.badRequest().body(response);
        }

        String otp = generateOTP();
        try {
            boolean emailSent = sendEmailWithOTP(email, otp);
            if (emailSent) {
                session.setAttribute("otp", otp);
                session.setAttribute("email", email);
                response.put("success", true);
                response.put("message", "OTP đã được gửi đến email");
                System.out.println("OTP đã gửi: " + otp + " | Email: " + email + " | Session: " + session.getId());
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Không thể gửi email OTP");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi gửi OTP: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

	@PostMapping("/verify-otp")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest, HttpSession session) {
	    String email = verifyOtpRequest.getEmail();
	    String otp = verifyOtpRequest.getOtp();
	    String sessionOtp = (String) session.getAttribute("otp");
	    String sessionEmail = (String) session.getAttribute("email");
	    Map<String, Object> response = new HashMap<>();
	    System.out.println("OTP kiểm tra: " + otp + " | Email: " + email);
	    System.out.println("OTP trong session: " + session.getAttribute("otp") + " | Email session: " + session.getAttribute("email") + " | Session: " + session.getId());

	    if (email == null || otp == null ||email.trim().isEmpty() || otp.trim().isEmpty()) {
	        response.put("success", false);
	        response.put("message", "Thông tin không được để trống");
	        return ResponseEntity.badRequest().body(response);
	    }

	    if (sessionOtp == null || sessionEmail == null || !sessionEmail.equals(email) || !sessionOtp.equals(otp)) {
	        response.put("success", false);
	        response.put("message", "OTP không đúng hoặc đã hết hạn");
	        return ResponseEntity.badRequest().body(response);
	    }

	    NguoiDungDao nguoiDungDao = new NguoiDungDao();
	    NguoiDung nguoiDung = new NguoiDung();
	    nguoiDung.setEmail(email);
	    nguoiDung.setMatKhau("123");
	    nguoiDung.setTrangThai(1);
	    nguoiDung.setIdPhanQuyen(1);

	    try {
	        nguoiDungDao.save(nguoiDung);
	        session.removeAttribute("otp");
	        session.removeAttribute("email");
	        response.put("success", true);
	        response.put("message", "Đăng ký thành công!");
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Lỗi khi đăng ký: " + e.getMessage());
	        return ResponseEntity.status(500).body(response);
	    }

	    return ResponseEntity.ok(response);
	}

    private String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    private boolean sendEmailWithOTP(String email, String otp) {
        String subject = "FutaBus - Xác nhận đăng ký";
        String body = "Mã OTP của bạn là: " + otp + "\nVui lòng sử dụng mã này để hoàn tất đăng ký.";
        try {
            sendEmail(email, subject, body);
            return true;
        } catch (Exception e) {
            System.out.println("Gửi email thất bại: " + e.getMessage());
            return false;
        }
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}