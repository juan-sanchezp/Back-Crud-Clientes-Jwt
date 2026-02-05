
package co.edu.unicauca.asae.core.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.asae.core.modelo.Cliente;
import co.edu.unicauca.asae.core.modelo.Region;
import co.edu.unicauca.asae.core.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository servicioAccesdoBDCliente;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) servicioAccesdoBDCliente.findAll();
	}

	@Override
	public Cliente save(Cliente objCliente) {

		return servicioAccesdoBDCliente.save(objCliente);
	}

	@Override
	public Cliente findById(Integer id) {
		return servicioAccesdoBDCliente.findById(id).orElse(null);

	}

	@Override
	public void delete(Integer id) {
		servicioAccesdoBDCliente.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return servicioAccesdoBDCliente.findAllRegiones();
	}

}
