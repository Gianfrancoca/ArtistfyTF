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

import pe.edu.upc.entity.Event;
import pe.edu.upc.serviceinterface.IEventService;
import pe.edu.upc.serviceinterface.IOrganizerService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private IEventService eS;
	@Autowired
	private IOrganizerService oS;

	@GetMapping("/new")
	public String newEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("listOrganizers", oS.listOrganizer());
		return "event/event";
	}

	@PostMapping("/save")
	public String saveEvent(@Validated Event event, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listOrganizers", oS.listOrganizer());
			return "event/event";
		} else {
			eS.insert(event);
			model.addAttribute("mensaje", "Evento se registro correctamente");
			return "event/event";
		}
	}

	@GetMapping("/list")
	public String listEvents(Model model) {
		try {
			model.addAttribute("listEvents", eS.listEvent());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "event/listEvents";
	}

	@RequestMapping("/delete/{id}")
	public String deleteContract(Model model, @PathVariable(value = "id") int id) {
		try {
			model.addAttribute("event", new Event());
			if (id > 0) {
				eS.delete(id);
			}

			model.addAttribute("mensaje", "Se eliminó correctamente.");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("mensaje", "Ocurrió un error, no se pudo eliminar");
		}
		model.addAttribute("listEvents", eS.listEvent());
		return "event/listEvents";
	}

	@RequestMapping("/irupdate/{id}")
	public String irupdate(Model model, @PathVariable int id, RedirectAttributes objRedir) {

		Optional<Event> objPro = eS.searchId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/events/list";
		} else {
			model.addAttribute("listOrganizers", oS.listOrganizer());
			model.addAttribute("event", objPro.get());
			return "event/event";
		}
	}

}
