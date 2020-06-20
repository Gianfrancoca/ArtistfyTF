package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Genres")
public class Genre implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGenre;
	
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El genero no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El genero no puede contener un número")
	@Column(name = "nameGenre", nullable = false, length = 20)
	private String nameGenre;


	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Genre(int idGenre,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El genero no puede contener un caracter especial") @Pattern(regexp = "[^0-9]+", message = "El genero no puede contener un número") String nameGenre) {
		super();
		this.idGenre = idGenre;
		this.nameGenre = nameGenre;
	}


	public int getIdGenre() {
		return idGenre;
	}


	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}


	public String getNameGenre() {
		return nameGenre;
	}


	public void setNameGenre(String nameGenre) {
		this.nameGenre = nameGenre;
	}
	
}
