package ch.hearc.beer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.beer.model.Beer;
import ch.hearc.beer.model.IBeerDatabase;
import ch.hearc.beer.model.MapBeerDatabase;

@Controller
@RequestMapping("/beer")
public class BeerController {
	private final int BEER_INIT = 30;
	private IBeerDatabase database;

	public BeerController() {
		database = new MapBeerDatabase(BEER_INIT);
	}
	
	/**Light controller : Its role is juste to verify input from user to protect DB */
	//verify beer fields
	private boolean verify(Beer beer) {
		return beer.getName() != ""
				&& beer.getAlcoholPourcentage() >= 0 //allow alcohol-free beer, as i do not drink alcohol :-)
				&& beer.getPrice() >= 0; //allow free beer since i also love free things
	}
	
	/** CONTROLLER WEB MAPPING - Rest API **/
	
	//INDEX : Simple GET, show all beers
	@GetMapping(value={"", "/", "/index"})
	public String indexBeers(Model model, HttpSession session){
		System.out.println("Index");
		model.addAttribute("beers", database.getAllBeers());
		
		// verify if session has a message (errror or success), and consume it (forward to model)
		// so that it appears only once to user
		if(session.getAttribute("error") != null) {
			model.addAttribute("error", session.getAttribute("error"));
			session.removeAttribute("error");
		}
		if(session.getAttribute("success") != null) {
			model.addAttribute("success", session.getAttribute("success"));
			session.removeAttribute("success");
		}
		return "index";
	}
	
	//CREATE VIEW : get + create, Show the create form
	@GetMapping(value="/create")
	public String create(Model model){
		System.out.println("create form");
		//create "fake/temporary" beer (id=-1) to let the client use the "update" view artificially
		Beer beer = new Beer("", 0, 0, -1);
		model.addAttribute("beer", beer);
		return "createOrUpdate";
	}
	
	//CREATE FORM : post, create new beer
	@PostMapping
	public String store(@ModelAttribute Beer beer, HttpSession session){
		System.out.println("create");
		if(verify(beer)) {
			session.setAttribute("success","Beer successfully created");
			database.create(beer);
		} else {
			session.setAttribute("error","Error while creating Beer");
		}
		//always redirect if not get - not idempotent
		return "redirect:/beer/index";
	}
	
	//UPDATE VIEW : get + /id, Get specific beer form (update, for our project)
	@GetMapping(value="/{id}")
	public String editForm(@PathVariable("id") int id, Model model){
		System.out.println("update form");
		model.addAttribute("beer", database.load(id));
		return "createOrUpdate";
	}
	
	//UPDATE FORM : put, update a beer in db
	@PutMapping
	public String edit(@ModelAttribute Beer beer, HttpSession session) {
		System.out.println("update");
		if(verify(beer)) {
			session.setAttribute("success","Beer updated !");
			database.save(beer);
		} else {
			session.setAttribute("error","Error while updating beer");
		}
		//always redirect if not get - not idempotent
		return "redirect:/beer/index";
	}
	
	//DELETE : Delete beer
	@DeleteMapping(value="/{id}")
	public String destroy(@PathVariable("id") int id, HttpSession session) {
		System.out.println("delete");
		if(database.delete(id)) {
			session.setAttribute("success","Beer successfully deleted");
		} else {
			session.setAttribute("error","Error while deleting Beer");
		}
		return "redirect:/beer/index";
	}
}
