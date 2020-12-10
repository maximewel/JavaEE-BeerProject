package ch.hearc.beer.model;

import java.util.concurrent.ThreadLocalRandom;

public class Beer {
	//attributes
	private String name;
	private float alcoholPourcentage;
	private int price;
	private int id;
	
	private static String[] beerNames = {"Müller Bräu", "Orforte", "Père Porret", "Rosengarten", "Rugenbräu", 
			"Schützengarten", "Sierrevoise", "Spok", "Tell", "Trois Dames", "Ueli Bier",
			"Unser Bier", "Valaisanne", "Wädi-Brau", "Warteck", "Weizentrumpf"};
	
	//constructor
	public Beer(String name, float alcoholPourcentage, int price, int id) {
		super();
		this.name = name;
		this.alcoholPourcentage = alcoholPourcentage;
		this.price = price;
		this.id = id;
	}
	
	public static Beer createBeer(int id) {
		return new Beer(randomBeerName(), 
						ThreadLocalRandom.current().nextFloat() * 100f, 
						ThreadLocalRandom.current().nextInt(100),
						id);
	}
	
	public static String randomBeerName() {
		return beerNames[ThreadLocalRandom.current().nextInt(beerNames.length)];
	}
	
	// getters/setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getAlcoholPourcentage() {
		return alcoholPourcentage;
	}
	public void setAlcoholPourcentage(float alcoholPourcentage) {
		this.alcoholPourcentage = alcoholPourcentage;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
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
