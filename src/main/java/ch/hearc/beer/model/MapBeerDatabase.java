package ch.hearc.beer.model;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* Beer database, non persistent, using map */
public class MapBeerDatabase implements IBeerDatabase {
	//Simulate database. Map : internal ID of beer != ID of beer in the map as seen in the JEE course
	Map<Integer, Beer> beerMap;
	
	/**
	 * Search for the next available, free internal ID for a beer
	 * This internal ID has no guarantee to match the map entry number of the beer
	 * @return the first free ID
	 */
	private int getNextId() {
		//we could also have implemented an auto-increment, faster but less economic in values
		int i=0;
		while(beerMap.containsKey(i))
			i++;
		return i;
	}
	
	/**
	 * Just a little constructor to seed the Controller map with datas
	 */
	public MapBeerDatabase(int initialBeers) {
		beerMap = IntStream.range(0, initialBeers)
				.parallel()
				.mapToObj(Beer::createBeer)
				.collect(Collectors.toMap(Beer::getId, Function.identity()));
	}
	

	@Override
	public void create(Beer beer) {
		beer.setId(getNextId());
		beerMap.put(beer.getId(), beer);
	}

	@Override
	public Beer load(int id) {
		return beerMap.get(id);
	}

	@Override
	public void save(Beer beer) {
		beerMap.replace(beer.getId(), beer);
	}

	@Override
	public void delete(Beer beer) {
		delete(beer.getId());
	}
	
	@Override
	public boolean delete(int id) {
		Beer removed = beerMap.remove(id);
		return (removed != null);
	}

	@Override
	public Collection<Beer> getAllBeers() {
		return beerMap.values();
	}

}
