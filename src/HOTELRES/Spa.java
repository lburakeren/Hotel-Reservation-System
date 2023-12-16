package HOTELRES;

public class Spa extends Services {
	private int days ;
	private double spaCost = 100 ;
	
	public Spa(int days, double spaCost , int customerID) {
		super(customerID);
		this.days = days;
		this.spaCost = spaCost;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getSpaCost() {
		return spaCost;
	}

	public void setSpaCost(double spaCost) {
		this.spaCost = spaCost;
	}
	
    public String getType() {
        return "Spa";
    }
    

    public double calculatePrice() {
    	double total_price =  (days * spaCost) ;
    	return total_price ;
    }

	@Override
	public double getCost() {
		return calculatePrice() ;
	}
	  
	
}
