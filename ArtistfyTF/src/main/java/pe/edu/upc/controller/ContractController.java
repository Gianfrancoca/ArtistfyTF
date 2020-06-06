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
import pe.edu.upc.entity.Contract;
import pe.edu.upc.serviceinterface.IArtistService;
import pe.edu.upc.serviceinterface.IContractService;

@Controller
@RequestMapping("/contracts")
public class ContractController {
	
	@Autowired
	private IContractService cS;

	@Autowired
	private IArtistService aS;
	
	@GetMapping("/new")
	public String newContract(Model model) {
		model.addAttribute("contract", new Contract());
		model.addAttribute("listArtists", aS.listArtist());
		return "contract/contract";
	}
	
	@PostMapping("/save")
	public String saveContract(@Validated Contract contract, BindingResult result, Model model) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("listArtists", aS.listArtist());
			return "contract/contract";
		} else {
			cS.insert(contract);
			model.addAttribute("mensaje", "Contrato se registro correctamente");
			return "contract/contract";
		}
	}
	
	@GetMapping("/list")
	public String listContracts (Model model) {
		try {
			model.addAttribute("listContracts", cS.listContract());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "contract/listContracts";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteContract(Model model, @PathVariable(value="id") int id){
		try {
			if(id>0) {
				cS.delete(id);
			}
			
			model.addAttribute("mensaje","Se eliminó correctamente.");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("mensaje","Ocurrió un error, no se pudo eliminar");
		}
		model.addAttribute("listContracts", cS.listContract());
		return "contract/listContracts";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irupdate(Model model, @PathVariable int id, RedirectAttributes objRedir) {
		
		Optional<Contract> objPro=cS.searchId(id);
		if(objPro == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/contracts/list";
		} else {
			model.addAttribute("listArtists", aS.listArtist());
			model.addAttribute("contract", objPro.get());
			return "contract/contract";
		}
	}
}
