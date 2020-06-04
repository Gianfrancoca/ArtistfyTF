package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Organizer;

@Repository
public interface IOrganizerRepository extends JpaRepository<Organizer, Integer>{

	@Query("select count(o.dni) from Organizer o where o.dni=:dni")
	public int searchDni(@Param("dni") int documento);
	
}
