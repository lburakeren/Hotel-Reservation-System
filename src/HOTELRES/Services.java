package HOTELRES;

public abstract class Services implements Calculable{

    private int customerId;


    public Services(int customerId) {
        this.customerId = customerId;
    }

  
    public int getCustomerId() {
		return customerId;
	}


    public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public abstract String getType();
	
	public abstract double calculatePrice();
	
	public void displayServiceInfo() {
		System.out.println("Customer ID : " + this.customerId + ", Service Type : " + this.getType() + ", Cost : " + this.calculatePrice() );
	}
	
	
}