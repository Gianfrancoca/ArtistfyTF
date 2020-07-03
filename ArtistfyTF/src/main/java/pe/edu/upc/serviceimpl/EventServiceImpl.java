package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Event;
import pe.edu.upc.repository.IEventRepository;
import pe.edu.upc.serviceinterface.IEventService;

@Service
public class EventServiceImpl implements Serializable, IEventService{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IEventRepository eR;

	@Override
	public void insert(Event event) {
		// TODO Auto-generated method stub
		eR.save(event);
	}

	@Override
	public List<Event> listEvent() {
		// TODO Auto-generated method stub
		return eR.findAll();
	}

	@Override
	public void delete(int idEvent) {
		// TODO Auto-generated method stub
		eR.deleteById(idEvent);
	}

	@Override
	public Optional<Event> searchId(int idEvent) {
		// TODO Auto-generated method stub
		return eR.findById(idEvent);
	}

	@Override
	public List<Event> findNameEventFull(String name) {
		// TODO Auto-generated method stub
		return eR.search(name);
	}

	@Override
	public List<String[]> eventOrganizer() {
		// TODO Auto-generated method stub
		return eR.eventOrganizer();
	}


}
