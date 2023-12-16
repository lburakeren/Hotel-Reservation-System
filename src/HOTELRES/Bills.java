package HOTELRES;

public class Bills implements Calculable{
	
	private String type ;
	private double amount ;
	private String month ;
	
	public Bills(String type, double amount, String month) {
		super();
		this.type = type;
		this.amount = amount;
		this.month = month;
	}
	
	
	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}



	@Override
	public double getCost() {
		return getAmount();
	}

}
