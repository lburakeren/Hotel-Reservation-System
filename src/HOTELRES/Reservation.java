package HOTELRES;

import java.util.List;
import java.util.ArrayList ;

public class Reservation extends Services implements Comparable<Reservation> {
	
	private static int totalNumOfReservations=0;
	private List<Services> services;
	
	private Room room;
	private String reservationMonth;
	private String hotelName ;
	private int reservationStart;
	private int reservationEnd;

	public Reservation(Room room, String hotelName ,String reservationMonth, int reservationStart, int reservationEnd, int customerID) {
	    super(customerID);
		this.room = room;
	    this.reservationMonth = reservationMonth;
	    this.reservationStart = reservationStart;
	    this.reservationEnd = reservationEnd;
	    this.hotelName = hotelName ;
	    this.services = new ArrayList<Services>();
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public String getHotelName() {
		return hotelName;
	}

	public Room getRoom() {
	    return room;
	}

	public void setRoom(Room room) {
	    this.room = room;
	}
	
	public String getReservationMonth() {
	    return reservationMonth;
	}

	public void setReservationMonth(String reservationMonth) {
	    this.reservationMonth = reservationMonth;
	}

	public int getReservationStart() {
	    return reservationStart;
	}

	public void setReservationStart(int reservationStart) {
	    this.reservationStart = reservationStart;
	}

	public int getReservationEnd() {
	    return reservationEnd;
	}

	public void setReservationEnd(int reservationEnd) {
	    this.reservationEnd = reservationEnd;
	}



	public String displayInfo() {
	    return "Reservation ID #"+ this.getCustomerId() +"\nReservation for a " + room.getRoomType() + " room in " + getHotelName() + " starts on " + getReservationMonth() + " " + getReservationStart() + " and ends on " + getReservationMonth() + " " + getReservationEnd() +". Reservation has a total cost of $" + calculatePrice() + ".";
	}

	private int getMultiplier(String month) {
	    if (month.equals("June") || month.equals("July") || month.equals("August")) {
	        return 2;
	    }
	    return 1;
	}

	public static int getTotalNumOfReservations() {
		return totalNumOfReservations;
	}
	
	public static void setTotalNumOfReservations(int a) {
		totalNumOfReservations+= a ;
	}
	
    public String getType() {
        return "Room booking";
    }
    

	public void addService(Services service) {
		services.add(service);
	}

	public Services[] getServices() {
		return services.toArray(new Services[services.size()]);
	}

	@Override
	public double calculatePrice() {
	    int reservationPeriod = reservationEnd - reservationStart;
	    double totalPrice = reservationPeriod * room.getDailyCost() * getMultiplier(reservationMonth);
	    return totalPrice;
	}

	@Override
	public double getCost() {
		return calculatePrice();
	}

	@Override
	public int compareTo(Reservation o) {
		return this.hotelName.compareTo(o.getHotelName());
	}


	   
}
