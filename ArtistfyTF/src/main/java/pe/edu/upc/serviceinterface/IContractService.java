package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Contract;

public interface IContractService {

	public void insert(Contract contract);
	List<Contract> listContract();
	public void delete(int idContract);
	Optional<Contract> searchId(int idContract);
	
	public List<String[]> contractReport();
}
