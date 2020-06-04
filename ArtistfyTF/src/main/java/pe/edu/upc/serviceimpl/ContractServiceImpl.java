package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Contract;
import pe.edu.upc.repository.IContractRepository;
import pe.edu.upc.serviceinterface.IContractService;

@Service
public class ContractServiceImpl implements Serializable, IContractService{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IContractRepository cR;
	
	@Override
	public void insert(Contract contract) {
		// TODO Auto-generated method stub
		cR.save(contract);
	}

	@Override
	public List<Contract> listContract() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

}
