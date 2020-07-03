package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upc.entity.Genre;
import pe.edu.upc.serviceinterface.IGenreService;

@Controller
@RequestMapping("/genres")
@Secured({"ROLE_ADMIN","ROLE_ARTIST"})
public class GenreController {
	
	@Autowired
	private IGenreService gS;
	
	@GetMapping("/new")
	public String newGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "genre/genre";
	}
	
	@GetMapping("/list")
	public String listGenre(Model model) {
		try {
			model.addAttribute("listGenres", gS.listGenre());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "genre/listGenres";
	}
	
	@PostMapping("/save")
	public String saveGenre(@Validated Genre genre, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listGenres", gS.listGenre());
			return "genre/genre";
		} else {
			gS.insert(genre);
			//model.addAttribute("mensaje", "Genero se registro correctamente");
			model.addAttribute("listGenres", gS.listGenre());
			return "genre/listGenres";
		}
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteGenre(Model model, @PathVariable(value = "id") int id) {
		try {
			model.addAttribute("genre", new Genre());
			if (id > 0) {
				gS.delete(id);
			}

			model.addAttribute("mensaje", "Se eliminó correctamente.");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("mensaje", "Ocurrió un error, no se pudo eliminar");
		}
		model.addAttribute("listGenres", gS.listGenre());
		return "genre/listGenres";
	}

}
