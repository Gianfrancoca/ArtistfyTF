package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Role;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.serviceinterface.IRolService;

@Service
public class RolServiceImpl implements IRolService{
	
	@Autowired
	private IRoleRepository rR;

	@Override
	public void insert(Role role) {
		// TODO Auto-generated method stub
		rR.save(role);
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

}
