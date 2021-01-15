package ch.hearc.beer.model;

import java.util.Collection;

public interface IBeerDatabase {
	/* Defines a Basic CRUD interface */
	
	//CRUD
	public void create(Beer beer); //create

	public Beer load(int id); //read
		
	public void save(Beer beer); //update
	
	public void delete(Beer beer); //delete
	//another delete more convenient for websites (we get with ID a lot)
	public boolean delete(int id);
	
	public Collection<Beer> getAllBeers();
}