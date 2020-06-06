package pe.edu.upc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Event;



@Repository
public interface IEventRepository extends JpaRepository<Event, Integer>{


}
