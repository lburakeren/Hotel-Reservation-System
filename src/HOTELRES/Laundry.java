package HOTELRES;

public class Laundry extends Services {
	private int clothingPieces ;
	private double laundryCost = 20 ;
	
	public Laundry( int customerId, int clothingPieces, double laundryCost) {
		super(customerId);
		this.clothingPieces = clothingPieces;
		this.laundryCost = laundryCost;
	}

	public int getClothingPieces() {
		return clothingPieces;
	}

	public void setClothingPieces(int clothingPieces) {
		this.clothingPieces = clothingPieces;
	}

	public double getLaundryCost() {
		return laundryCost;
	}

	public void setLaundryCost(double laundryCost) {
		this.laundryCost = laundryCost;
	}
	
    public String getType() {
        return "Laundry";
    }
    

    public double calculatePrice() {
    	double total_price =  (clothingPieces * laundryCost) ;
    	return total_price ;
    }

	@Override
	public double getCost() {
		return calculatePrice();
	}
	
	

}
