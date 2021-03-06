package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
	public int insert(Artist artist) {
		// TODO Auto-generated method stub
		int rpta=aR.searchDni(artist.getDni());
		if(rpta==0) {
			aR.save(artist);
		}
		return rpta;
	}

	@Override
	public List<Artist> listArtist() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}

	@Override
	public void delete(int idArtist) {
		// TODO Auto-generated method stub
		aR.deleteById(idArtist);
	}

	@Override
	public Optional<Artist> searchId(int idArtist) {
		// TODO Auto-generated method stub
		return aR.findById(idArtist);
	}

	@Override
	public void update(Artist artist) {
		// TODO Auto-generated method stub
		aR.save(artist);
	}

}
