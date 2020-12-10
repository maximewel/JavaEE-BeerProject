package ch.hearc.beer.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.beer.model.Beer;

@RestController
@RequestMapping("/beer")
public class BeerController {
	//Simulate database
	Map<Integer, Beer> beerMap;
	private final int BEER_INIT = 10;
	
	/**
	 * Search for the next available, free ID for a beer
	 * @return the first free ID
	 */
	private int getNextId() {
		int i=0;
		while(beerMap.containsKey(i))
			i++;
		return i;
	}
	
	/**
	 * Just a little constructor to seed the Controller map with datas
	 */
	public BeerController() {
		beerMap = IntStream.range(0, BEER_INIT)
				.parallel()
				.mapToObj(Beer::createBeer)
				.collect(Collectors.toMap(Beer::getId, Function.identity()));
	}
	
	@ResponseBody
	@GetMapping
	public Map<Integer, Beer> indexBeers(){
		return beerMap;
	}
	
	@ResponseBody
	@GetMapping(value="/{id}")
	public Beer showBeer(@PathVariable("id") String id){
		try {
			int i = Integer.parseInt(id);
			return beerMap.get(i);
		}catch(Exception e) {
			return null;
		}
	}
	
	@ResponseBody
	@PostMapping
	public void store(	@RequestParam("name") String name, 
						@RequestParam("alcohol") Float alcoholPourcentage,
						@RequestParam("price") int price) {
		int id = getNextId();
		beerMap.put(id, new Beer(name, alcoholPourcentage, price, id));
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}/delete")
	public void destroy(@PathVariable("id") String id) {
		beerMap.remove(id);
	}
}
