package pe.edu.upc.controller;
import java.util.List;
import java.util.Map;
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
import pe.edu.upc.entity.Event;
import pe.edu.upc.serviceinterface.IEventService;
import pe.edu.upc.serviceinterface.IOrganizerService;

@Controller
@RequestMapping("/events")
@Secured("ROLE_ADMIN")
@Secured({"ROLE_ADMIN", "ROLE_ARTIST","ROLE_ORGANIZER"})
public class EventController {

	@Autowired
	private IEventService eS;
	@Autowired
	private IOrganizerService oS;


	@Secured({"ROLE_ADMIN","ROLE_ORGANIZER"})
	@GetMapping("/new")
	public String newEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("listEvents", eS.listEvent());
		model.addAttribute("listOrganizers", oS.listOrganizer());
		return "event/event";
	}

	@Secured({"ROLE_ADMIN","ROLE_ORGANIZER"})
	@PostMapping("/save")
	public String saveEvent(@Validated Event event, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listOrganizers", oS.listOrganizer());
			model.addAttribute("listEvents", eS.listEvent());
			return "event/event";
		} else {
			eS.insert(event);
			//model.addAttribute("organizer", new Organizer());
			model.addAttribute("event", new Event());
			model.addAttribute("listEvents", eS.listEvent());
			model.addAttribute("listOrganizers", oS.listOrganizer());
			return "redirect:/events/list";
		}
	}
	@GetMapping("/list")
	public String listEvents(Model model) {
		try {
			model.addAttribute("event", new Event());
			model.addAttribute("listEvents", eS.listEvent());
			model.addAttribute("listOrganizers", oS.listOrganizer());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "event/listEvents";
	}