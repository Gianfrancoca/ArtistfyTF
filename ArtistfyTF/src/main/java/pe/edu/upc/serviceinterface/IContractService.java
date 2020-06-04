package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Contract;

public interface IContractService {

	public void insert(Contract contract);
	List<Contract> listContract();
}
