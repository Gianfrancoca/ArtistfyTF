package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Artist;

public interface IArtistService {

	public int insert(Artist artist);
	List<Artist> listArtist();
	public void delete(int idArtist);
	Optional<Artist> searchId(int idArtist);
}
