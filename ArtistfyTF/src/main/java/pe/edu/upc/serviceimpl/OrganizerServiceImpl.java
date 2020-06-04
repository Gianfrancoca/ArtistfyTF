package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Organizer;
import pe.edu.upc.repository.IOrganizerRepository;
import pe.edu.upc.serviceinterface.IOrganizerService;

@Service
public class OrganizerServiceImpl implements Serializable, IOrganizerService {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizerRepository oR;
	
	@Override
	public int insert(Organizer organizer) {
		// TODO Auto-generated method stub
		int rpta=oR.searchDni(organizer.getDni());
		if(rpta==0) {
			oR.save(organizer);
		}
		return rpta;
	}

	@Override
	public List<Organizer> listOrganizer() {
		// TODO Auto-generated method stub
		return oR.findAll();
	}

}
