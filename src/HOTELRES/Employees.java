package HOTELRES;

public class Employees implements Calculable {

	private String name ;
	private String surname ;
	private double monthlyPayment;
	private int id ;
	
	public Employees(String name, String surname, double monthlyPayment, int id) {
		super();
		this.name = name;
		this.surname = surname;
		this.monthlyPayment = monthlyPayment;
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public double getMonthlyPayment() {
		return monthlyPayment;
	}


	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public double getCost() {
		return getMonthlyPayment();
	}

}
