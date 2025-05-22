package FutaBus.bean;

public class OrderResponse {
	
    private String orderUrl;
    private String qrCode;
    private long orderCode;   
    private long amount;   

    public OrderResponse() {
    }

    public OrderResponse(String orderUrl, String qrCode, long orderCode, long amount) {
        this.orderUrl = orderUrl;
        this.qrCode = qrCode;
        this.orderCode = orderCode;
        this.amount = amount;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(long orderCode) {
        this.orderCode = orderCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}