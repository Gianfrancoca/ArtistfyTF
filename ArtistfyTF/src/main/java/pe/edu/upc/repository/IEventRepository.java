package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Event;

@Repository
public interface IEventRepository extends JpaRepository<Event, Integer>{

	@Query("from Event e where e.name like %:busqueda%  or e.organizer.firstName like %:busqueda%")
	List<Event> search(@Param("busqueda") String busqueda);
	
	@Query(value="select f.first_name , count(ev.id_event) from events ev join organizers f on ev.id_organizer=f.id_organizer group by f.first_name order by count(f.first_name) desc", nativeQuery = true)
	public List<String[]> eventOrganizer();
	

	
	
}