package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.entity.Artist;
import pe.edu.upc.serviceinterface.IArtistService;

@Controller
@RequestMapping("/artists")
public class ArtistController {

	@Autowired
	private IArtistService aS;
	
	@GetMapping("/new")
	public String newArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "artist/artist";
	}
	
	@PostMapping("/save")
	public String saveArtist(@Validated Artist artist, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			return "artist/artist";
		} else {
			aS.insert(artist);
			model.addAttribute("listArtists", aS.listArtist());
			return "artist/listArtists";
		}
	}
	
	@GetMapping("/list")
	public String listArtists(Model model) {
		try {
			model.addAttribute("listArtists", aS.listArtist());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "artist/listArtists";
	}
	
}
