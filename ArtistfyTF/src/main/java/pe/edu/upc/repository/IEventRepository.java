package pe.edu.upc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Event;



@Repository
public interface IEventRepository extends JpaRepository<Event, Integer>{

	@Query("from Event e where e.name like %:busqueda%  or e.organizer.firstName like %:busqueda%")
	List<Event> search(@Param("busqueda") String busqueda);
	
}
