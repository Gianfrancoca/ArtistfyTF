package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

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

}
