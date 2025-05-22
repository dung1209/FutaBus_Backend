package FutaBus.bean;

public class OrderRequest {
	
    private long orderCode;  
    private long amount;
    private String description; 
    private String returnUrl;
    private String cancelUrl; 

    public OrderRequest() {
    }

    public OrderRequest(long orderCode, long amount, String description, String returnUrl, String cancelUrl) {
        this.orderCode = orderCode;
        this.amount = amount;
        this.description = description;
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
}