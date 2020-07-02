package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Organizer;
import pe.edu.upc.serviceinterface.IOrganizerService;
import pe.edu.upc.serviceinterface.IUploadService;

@Controller
@RequestMapping("/organizers")
//@Secured({"ROLE_ADMIN", "ROLE_ORGANIZER"})
@Secured("ROLE_ADMIN")
public class OrganizerController {

	@Autowired
	private IOrganizerService oS;
	
	@Autowired
	private IUploadService uploadFileService;
	
	
	@GetMapping("/new")
	public String newOrganizer(Model model) {
		model.addAttribute("organizer", new Organizer());
		return "organizer/organizer";
	}
	
	@PostMapping("/save")
	public String saveOrganizer(@Validated Organizer organizer, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception{
		
		if(result.hasErrors()) {
			return "organizer/organizer";
		}else {
			
			if (!foto.isEmpty()) {

				if (organizer.getIdOrganizer() > 0 && organizer.getFoto() != null && organizer.getFoto().length() > 0) {

					uploadFileService.delete(organizer.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				organizer.setFoto(uniqueFilename);
			}
			
			
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
	
	@PostMapping("/update")
	public String updatteOrganizer(@Validated Organizer organizer, BindingResult result, Model model) throws Exception{
		
		if(result.hasErrors()) {
			return "organizer/organizer";
		}else {
			
			oS.update(organizer);
			model.addAttribute("listOrganizers", oS.listOrganizer());
			model.addAttribute("mensaje","Se actualizó correctamente");
			return "organizer/listOrganizers";
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
	
	@Secured("ROLE_ADMIN")
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
			return "organizer/organizerupd";
		}
	}
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	

	@GetMapping(value = "/viewOr/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Organizer> organizer = oS.searchId(id);
		if (organizer == null) {
			flash.addFlashAttribute("error", "El organizador no existe en la base de datos");
			return "redirect:/organizers/list";
		}

		model.addAttribute("organizer", organizer.get());

		return "organizer/viewOr";
	}
	
	
	
}
