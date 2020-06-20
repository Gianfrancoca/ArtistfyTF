package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Genre;
import pe.edu.upc.repository.IGenreRepository;
import pe.edu.upc.serviceinterface.IGenreService;

@Service
public class GenreServiceImpl implements Serializable, IGenreService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IGenreRepository gR;
	
	@Override
	public void insert(Genre genre) {
		// TODO Auto-generated method stub
		gR.save(genre);
	}

	@Override
	public List<Genre> listGenre() {
		// TODO Auto-generated method stub
		return gR.findAll();
	}

	@Override
	public void delete(int idGenre) {
		// TODO Auto-generated method stub
		gR.deleteById(idGenre);
	}

	@Override
	public Optional<Genre> searchId(int idGenre) {
		// TODO Auto-generated method stub
		return gR.findById(idGenre);
	}

}