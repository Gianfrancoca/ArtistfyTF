package pe.edu.upc.controller;

import java.util.Optional;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Organizer;
import pe.edu.upc.serviceinterface.IOrganizerService;

@Controller
@RequestMapping("/organizers")
@Secured("ROLE_ADMIN")
public class OrganizerController {

	@Autowired
	private IOrganizerService oS;
	
	@GetMapping("/new")
	public String newOrganizer(Model model) {
		model.addAttribute("organizer", new Organizer());
		return "organizer/organizer";
	}
	
	@PostMapping("/save")
	public String saveOrganizer(@Validated Organizer organizer, BindingResult result, Model model) throws Exception{
		
		if(result.hasErrors()) {
			return "organizer/organizer";
		}else {
			
			int rpta = oS.insert(organizer);
			if(rpta>0) {
				model.addAttribute("mensaje", "DNI ya existe.");
				return "organizer/organizer";
			}else {
				model.addAttribute("listOrganizers", oS.listOrganizer());
				return "organizer/listOrganizers";
			}
			
		}
		
	}
	
	@GetMapping("/list")
	public String listOrganizers(Model model) {
		try {
			model.addAttribute("listOrganizers", oS.listOrganizer());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "organizer/listOrganizers";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteArtist(Model model, @PathVariable(value="id") int id){
		try {
			if(id>0) {
				oS.delete(id);
			}
			
			model.addAttribute("mensaje","Se eliminó correctamente.");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("mensaje","Ocurrió un error, no se pudo eliminar");
		}
		model.addAttribute("listOrganizers", oS.listOrganizer());
		return "organizer/listOrganizers";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irupdate(Model model, @PathVariable int id, RedirectAttributes objRedir) {
		
		Optional<Organizer> objPro=oS.searchId(id);
		if(objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/organizers/list";
		} else {
			model.addAttribute("organizer", objPro.get());
			return "organizer/organizer";
		}
	}
	
	
}
