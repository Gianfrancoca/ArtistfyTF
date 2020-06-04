package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Artist;

public interface IArtistService {

	public int insert(Artist artist);
	List<Artist> listArtist();
	
}
