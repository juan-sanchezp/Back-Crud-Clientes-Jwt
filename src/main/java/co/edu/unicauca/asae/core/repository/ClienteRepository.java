

package co.edu.unicauca.asae.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.unicauca.asae.core.modelo.Cliente;
import co.edu.unicauca.asae.core.modelo.Region;


public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

    @Query("from Region")
    public List<Region> findAllRegiones();


    
}
