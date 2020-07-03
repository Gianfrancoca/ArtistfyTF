package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Artist;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, Integer>{

	@Query("select count(a.dni) from Artist a   where a.dni=:dni")
	public int searchDni(@Param("dni") int documento);   
}