package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Artist;
import pe.edu.upc.repository.IArtistRepository;
import pe.edu.upc.serviceinterface.IArtistService;

@Service
public class ArtistServiceImpl implements Serializable, IArtistService{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IArtistRepository aR;
	
	@Override
	public void insert(Artist artist) {
		// TODO Auto-generated method stub
		aR.save(artist);
	}

	@Override
	public List<Artist> listArtist() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}

}
