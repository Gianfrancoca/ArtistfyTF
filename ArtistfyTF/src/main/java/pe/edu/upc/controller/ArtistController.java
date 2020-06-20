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

import pe.edu.upc.entity.Artist;
import pe.edu.upc.serviceinterface.IArtistService;
import pe.edu.upc.serviceinterface.IGenreService;
import pe.edu.upc.serviceinterface.IUploadService;

@Controller
@RequestMapping("/artists")
@Secured("ROLE_ADMIN")
public class ArtistController {

	@Autowired
	private IArtistService aS;
	
	@Autowired
	private IGenreService gS;
	
	@Autowired
	private IUploadService uploadFileService;
	
	@GetMapping("/new")
	public String newArtist(Model model) {
		model.addAttribute("artist", new Artist());
		model.addAttribute("listGenres", gS.listGenre());
		return "artist/artist";
	}
	
	@PostMapping("/save")
	public String saveArtist(@Validated Artist artist, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listGenres",gS.listGenre());
			return "artist/artist";
		} else {
			
			if (!foto.isEmpty()) {

				if (artist.getIdArtist() > 0 && artist.getFoto() != null && artist.getFoto().length() > 0) {

					uploadFileService.delete(artist.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				artist.setFoto(uniqueFilename);
			}
	
			
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
			model.addAttribute("listGenres", gS.listGenre());
			model.addAttribute("artist", objPro.get());
			return "artist/artist";
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
	
	//@Secured("ROLE_ESTUDIANTE")
	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<Artist> artist = aS.searchId(id);
		if (artist == null) {
			flash.addFlashAttribute("error", "El artista no existe en la base de datos");
			return "redirect:/artists/list";
		}

		model.addAttribute("artist", artist.get());

		return "artist/view";
	}
	
}
