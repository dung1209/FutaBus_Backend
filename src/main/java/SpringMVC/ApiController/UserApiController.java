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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import FutaBus.bean.CreateAccountRequest;
import FutaBus.bean.NguoiDung;
import FutaBus.bean.OtpRequest;
import FutaBus.bean.PhieuDatVe;
import FutaBus.bean.PurchaseHistory;
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

@CrossOrigin(origins = "http://localhost:8086", allowCredentials = "true")
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
	public ResponseEntity<Map<String, Object>> confirmBooking(@RequestBody BookingWrapper wrapper, HttpSession session) {
		
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("currentUser");
		
		System.out.println("Session ID: " + session.getId());
		System.out.println("NguoiDung từ session: " + nguoiDung);
		
		if (nguoiDung == null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "error");
	        response.put("message", "Bạn cần phải đăng nhập trước khi đặt vé!");
	        return ResponseEntity.status(401).body(response);
	    }

		BookingRequest go = wrapper.getBookingData();
		BookingRequest back = wrapper.getBookingDataReturn();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		String idViTriGheStr = go.getIdViTriGhe();

		List<Integer> gheList = Arrays.stream(idViTriGheStr.split(",")).map(Integer::parseInt)
				.collect(Collectors.toList());

		PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
		phieuDatVeDao.saveBooking(go, nguoiDung.getIdNguoiDung());

		if (back != null && back.getIdViTriGhe() != null && !back.getIdViTriGhe().isEmpty()) {
			String idViTriGheReturnStr = back.getIdViTriGhe();
			List<Integer> gheReturnList = Arrays.stream(idViTriGheReturnStr.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			PhieuDatVeDao phieuDatVeReturnDao = new PhieuDatVeDao();
			phieuDatVeDao.saveBooking(back, nguoiDung.getIdNguoiDung());
		}

		Map<String, Object> response = new HashMap<>();
		response.put("status", "success");
		response.put("message", "Đặt vé thành công!");

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/confirmBooking1")
    public ResponseEntity<Map<String, Object>> confirmBooking(
        @RequestBody BookingWrapper wrapper,
        @RequestHeader("userid") int userid) {
		
		
		if (userid == -1) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "error");
	        response.put("message", "Bạn cần phải đăng nhập trước khi đặt vé!");
	        return ResponseEntity.status(401).body(response);
	    }

		BookingRequest go = wrapper.getBookingData();
		BookingRequest back = wrapper.getBookingDataReturn();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		String idViTriGheStr = go.getIdViTriGhe();

		List<Integer> gheList = Arrays.stream(idViTriGheStr.split(",")).map(Integer::parseInt)
				.collect(Collectors.toList());

		PhieuDatVeDao phieuDatVeDao = new PhieuDatVeDao();
		phieuDatVeDao.saveBooking(go, userid);

		if (back != null && back.getIdViTriGhe() != null && !back.getIdViTriGhe().isEmpty()) {
			String idViTriGheReturnStr = back.getIdViTriGhe();
			List<Integer> gheReturnList = Arrays.stream(idViTriGheReturnStr.split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			PhieuDatVeDao phieuDatVeReturnDao = new PhieuDatVeDao();
			phieuDatVeDao.saveBooking(back, userid);
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
	        if (nguoiDung.getHoTen() == null || nguoiDung.getHoTen().isEmpty()) {
	            nguoiDung.setHoTen("bạn");
	        }

	        response.put("nguoiDung", Map.of(
	            "idNguoiDung", nguoiDung.getIdNguoiDung(),
	            "idPhanQuyen", nguoiDung.getIdPhanQuyen(),
	            "hoTen", nguoiDung.getHoTen(),
	            "matKhau", nguoiDung.getMatKhau(),
	            "email", nguoiDung.getEmail()
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
	
	@PostMapping("/send-verify-otp")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> sendVerifyOtp(@RequestBody OtpRequest otpRequest, HttpSession session) {
	    String email = otpRequest.getEmail();
	    Map<String, Object> response = new HashMap<>();

	    if (email == null || email.trim().isEmpty()) {
	        response.put("success", false);
	        response.put("message", "Email không được để trống");
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

	    response.put("success", true);
	    response.put("message", "OTP hợp lệ! Bạn có thể nhập mật khẩu.");

	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/create-account")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createAccount(@RequestBody CreateAccountRequest request, HttpSession session) {
	    String email = request.getEmail();
	    String password = request.getPassword();

	    Map<String, Object> response = new HashMap<>();

	    String sessionEmail = (String) session.getAttribute("email");
	    if (sessionEmail == null || !sessionEmail.equals(email)) {
	        response.put("success", false);
	        response.put("message", "Email chưa được xác thực hoặc không khớp!");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	    }

	    if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
	        response.put("success", false);
	        response.put("message", "Thông tin không được để trống!");
	        return ResponseEntity.badRequest().body(response);
	    }

	    try {
	        NguoiDungDao nguoiDungDao = new NguoiDungDao();
	        NguoiDung nguoiDung = new NguoiDung();
	        nguoiDung.setEmail(email);
	        nguoiDung.setMatKhau(password); 
	        nguoiDung.setTrangThai(1);
	        nguoiDung.setIdPhanQuyen(1);
	        nguoiDung.setNgayDangKy(new Date());

	        nguoiDungDao.save(nguoiDung);

	        session.removeAttribute("otp");
	        session.removeAttribute("email");

	        response.put("success", true);
	        response.put("message", "Tạo tài khoản thành công!");
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Đã có lỗi khi tạo tài khoản: " + e.getMessage());
	        return ResponseEntity.status(500).body(response);
	    }
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
    
    @GetMapping("/general-information/{id}")
	public ResponseEntity<NguoiDung> getGeneralInformation(@PathVariable("id") Integer id) {
    	
    	NguoiDungDao nguoiDungDao = new NguoiDungDao();

    	NguoiDung nguoiDung = nguoiDungDao.getNguoiDungById(id);
    	
    	System.out.println("nguoiDung được in ra: " + nguoiDung);
        if (nguoiDung != null) {
            return ResponseEntity.ok(nguoiDung);
        } else {
            return ResponseEntity.notFound().build();
        }
	}

    @PostMapping("/update-user")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody NguoiDung nguoiDung) {
      Map<String, Object> response = new HashMap<>();

      if (nguoiDung == null || nguoiDung.getIdNguoiDung() == 0) {
          response.put("success", false);
          response.put("message", "Thông tin người dùng không hợp lệ!");
          return ResponseEntity.badRequest().body(response);
      }

      try {
          NguoiDungDao nguoiDungDao = new NguoiDungDao();

          NguoiDung existingUser = nguoiDungDao.getNguoiDungById(nguoiDung.getIdNguoiDung());
          if (existingUser == null) {
              response.put("success", false);
              response.put("message", "Người dùng không tồn tại!");
              return ResponseEntity.badRequest().body(response);
          }

          existingUser.setHoTen(nguoiDung.getHoTen());
          existingUser.setGioiTinh(nguoiDung.getGioiTinh());
          existingUser.setNamSinh(nguoiDung.getNamSinh());
          existingUser.setDiaChi(nguoiDung.getDiaChi());
          existingUser.setSoDienThoai(nguoiDung.getSoDienThoai());

          nguoiDungDao.updateNguoiDung(existingUser);

          response.put("success", true);
          response.put("message", "Cập nhật thông tin người dùng thành công!");
          return ResponseEntity.ok(response);
      } catch (Exception e) {
          response.put("success", false);
          response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
    }
    
    @PostMapping("/update-password")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody NguoiDung nguoiDung) {
      Map<String, Object> response = new HashMap<>();

      if (nguoiDung == null || nguoiDung.getIdNguoiDung() == 0) {
          response.put("success", false);
          response.put("message", "Thông tin người dùng không hợp lệ!");
          return ResponseEntity.badRequest().body(response);
      }

      try {
          NguoiDungDao nguoiDungDao = new NguoiDungDao();

          NguoiDung existingUser = nguoiDungDao.getNguoiDungById(nguoiDung.getIdNguoiDung());
          if (existingUser == null) {
              response.put("success", false);
              response.put("message", "Người dùng không tồn tại!");
              return ResponseEntity.badRequest().body(response);
          }

          existingUser.setMatKhau(nguoiDung.getMatKhau());

          nguoiDungDao.updateMatKhau(existingUser);

          response.put("success", true);
          response.put("message", "Cập nhật mật khẩu người dùng thành công!");
          return ResponseEntity.ok(response);
      } catch (Exception e) {
          response.put("success", false);
          response.put("message", "Lỗi khi cập nhật: " + e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
    }
    
    @GetMapping("/purchase-history/{idNguoiDung}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getLichSuMuaVe(@PathVariable int idNguoiDung) {
        Map<String, Object> response = new HashMap<>();

        try {
            PhieuDatVeDao dao = new PhieuDatVeDao();
            List<PurchaseHistory> dsVe = dao.getLichSuMuaVeByIdNguoiDung(idNguoiDung);

            List<Map<String, Object>> result = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for (PurchaseHistory record : dsVe) {
                Map<String, Object> data = new HashMap<>();
                String formattedThoiDiemDi = dateFormat.format(record.getThoiDiemDi());
                
                data.put("idPhieuDatVe", record.getIdPhieuDatVe());
                data.put("tenTuyen", record.getTenTuyen());
                data.put("thoiDiemDi", formattedThoiDiemDi);
                data.put("soLuongVe", record.getSoLuongVe());
                data.put("tongTien", record.getTongTien());
                data.put("trangThai", record.getTrangThai());
                data.put("danhSachIDGhe", record.getDanhSachIDGhe());
                result.add(data);
            }

            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi lấy dữ liệu: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    @PostMapping("/reset-password")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody CreateAccountRequest request) {
        String email = request.getEmail();
        String newPassword = request.getPassword();

        Map<String, Object> response = new HashMap<>();

        if (email == null || newPassword == null || email.trim().isEmpty() || newPassword.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Thông tin không được để trống!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            NguoiDungDao nguoiDungDao = new NguoiDungDao();
            NguoiDung nguoiDung = nguoiDungDao.findNguoiDungByEmail(email);

            if (nguoiDung == null) {
                response.put("success", false);
                response.put("message", "Email không tồn tại trong hệ thống!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            nguoiDung.setMatKhau(newPassword);
            nguoiDungDao.updateMatKhau(nguoiDung);

            response.put("success", true);
            response.put("message", "Cập nhật mật khẩu thành công!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Đã có lỗi khi cập nhật mật khẩu: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}