package ch.hearc.beer.model;

public class Beer {
	//attributes
	private String name;
	private int alcoholPourcentage;
	private double price;
	private int id;
	
	//constructor
	public Beer(String name, int alcoholPourcentage, double price, int id) {
		super();
		this.name = name;
		this.alcoholPourcentage = alcoholPourcentage;
		this.price = price;
		this.id = id;
	}
	
	// getters/setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAlcoholPourcentage() {
		return alcoholPourcentage;
	}
	public void setAlcoholPourcentage(int alcoholPourcentage) {
		this.alcoholPourcentage = alcoholPourcentage;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Beer [name=" + name + ", alcoholPourcentage=" + alcoholPourcentage + ", price=" + price + ", id=" + id
				+ "]";
	}
		
}
