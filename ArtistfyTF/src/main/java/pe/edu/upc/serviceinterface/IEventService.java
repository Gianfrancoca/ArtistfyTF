package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Event;

public interface IEventService {

	public void insert(Event event);
	List<Event> listEvent();
	
}
