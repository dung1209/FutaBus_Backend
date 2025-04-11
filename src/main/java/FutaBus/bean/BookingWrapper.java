package FutaBus.bean;

public class BookingWrapper {
	
	private BookingRequest bookingData;
    private BookingRequest bookingDataReturn;
    
    public BookingRequest getBookingData() {
        return bookingData;
    }

    public void setBookingData(BookingRequest bookingData) {
        this.bookingData = bookingData;
    }

    public BookingRequest getBookingDataReturn() {
        return bookingDataReturn;
    }

    public void setBookingDataReturn(BookingRequest bookingDataReturn) {
        this.bookingDataReturn = bookingDataReturn;
    }
	
}