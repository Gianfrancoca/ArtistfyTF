package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
