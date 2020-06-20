
package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Contract;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer>{
   
	
	
} 
