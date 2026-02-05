

package co.edu.unicauca.asae.core.services;

import java.util.List;

import co.edu.unicauca.asae.core.modelo.Cliente;
import co.edu.unicauca.asae.core.modelo.Region;


public interface IClienteService {
	
	public List<Cliente> findAll();
	public Cliente save(Cliente objCliente);
	public Cliente findById(Integer id);
	public void delete(Integer id);
	public List<Region> findAllRegiones();

}
