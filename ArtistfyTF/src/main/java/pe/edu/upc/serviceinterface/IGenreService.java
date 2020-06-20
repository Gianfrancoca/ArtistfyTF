package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Genre;

public interface IGenreService {
	
	public void insert(Genre genre);
	List<Genre> listGenre();
	public void delete(int idGenre);
	Optional<Genre> searchId(int idGenre);

}
