package ch.hearc.beer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.beer.model.Beer;

@RestController
@RequestMapping("/beer")
public class BeerController {
	//Simulate database
	Map<Integer, Beer> beerMap = new HashMap<Integer, Beer>();
	
	/**
	 * Just a little constructor to initalize the map with datas
	 */
	public BeerController() {
		beerMap.put(0, new Beer("Jus d'orange", 0, 5, 0));
		beerMap.put(1, new Beer("Feiknkllossen", 0, 6, 1));
		beerMap.put(2, new Beer("La pub sur youtuve", 10, 5, 2));
	}
	
	@ResponseBody
	@GetMapping
	public Map<Integer, Beer> showBeers(){
		return beerMap;
	}
	
	
	@ResponseBody
	@GetMapping(value="/beer/*")
	public Beer showBeer(){
		//return beerMap.get(id);
	}
}
