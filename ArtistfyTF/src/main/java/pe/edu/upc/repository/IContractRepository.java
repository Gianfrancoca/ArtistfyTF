
package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Contract;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer>{
   
	@Query(value="select first_name FirstName, address, salary from contracts Contract join artists FirstName on Contract.id_artist=FirstName.id_artist order by salary desc", nativeQuery = true)
	public List<String[]> contractReport();

	
} 
