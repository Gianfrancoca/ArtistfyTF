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
	@Secured({"ROLE_ADMIN","ROLE_ORGANIZER"})
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
	@Secured({"ROLE_ADMIN","ROLE_ORGANIZER"})
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
	
	@RequestMapping("/search")
	public String searchEvents(Model model, @Validated Event event) throws Exception{
	List<Event> listEvents;
	listEvents=eS.findNameEventFull(event.getName());
	if(listEvents.isEmpty()) {
		model.addAttribute("mensaje","No hay registros para su busqueda.");
	}
	model.addAttribute("listEvents", listEvents);
	return "event/listEvents";
		}
	
	@GetMapping(value = "/viewEv/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Event> event = eS.searchId(id);
		if (event == null) {
			flash.addFlashAttribute("error", "El evento no existe en la base de datos");
			return "redirect:/events/list";
		}

		model.addAttribute("event", event.get());

		return "event/viewEv";
	}
	
	@Secured({"ROLE_ADMIN","ROLE_ARTIST"})
	@RequestMapping("/reporte2")
	public String eventOrganizer(Map<String,Object>model) {
		model.put("listEventOrganizer", eS.eventOrganizer());
		return "reports/eventOrganizer";
	}
	

}
