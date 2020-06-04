package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Organizer;

public interface IOrganizerService {

	public int insert(Organizer organizer);
	List<Organizer> listOrganizer();
}
