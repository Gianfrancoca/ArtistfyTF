package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Organizer;

public interface IOrganizerService {

	public int insert(Organizer organizer);
	public void update(Organizer organizer);
	List<Organizer> listOrganizer();
	public void delete(int idOrganizer);
	Optional<Organizer> searchId(int idOrganizer);
}
