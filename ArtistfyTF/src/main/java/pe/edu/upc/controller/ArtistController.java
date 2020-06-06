package pe.edu.upc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			
			int rpta = aS.insert(artist);
			if(rpta>0) {
			model.addAttribute("mensaje", "DNI ya existe.");
			return "artist/artist";
			}else {
				model.addAttribute("listArtists", aS.listArtist());
				return "artist/listArtists";
			}
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
	
	@RequestMapping("/delete/{id}")
	public String deleteArtist(Model model, @PathVariable(value="id") int id){
		try {
			if(id>0) {
				aS.delete(id);
			}
			
			model.addAttribute("mensaje","Se eliminó correctamente.");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("mensaje","Ocurrió un error, no se pudo eliminar");
		}
		model.addAttribute("listArtists", aS.listArtist());
		return "artist/listArtists";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irupdate(Model model, @PathVariable int id, RedirectAttributes objRedir) {
		
		Optional<Artist> objPro=aS.searchId(id);
		if(objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/artists/list";
		} else {
			model.addAttribute("artist", objPro.get());
			return "artist/artist";
		}
	}
	
}
