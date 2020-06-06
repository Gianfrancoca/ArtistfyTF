package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Event;

public interface IEventService {

	public void insert(Event event);
	List<Event> listEvent();
	public void delete (int idEvent);
	Optional<Event> searchId(int idEvent);
	
}
