package co.edu.unicauca.asae.core.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae.core.modelo.Cliente;
import co.edu.unicauca.asae.core.modelo.Region;
import co.edu.unicauca.asae.core.services.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = { "http://localhost:4200" }) //permite que el back acepte peticiones de angular

@Tag(name = "Clientes", description = "Operaciones relacionadas con clientes y regiones")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@Operation(
		summary = "Listar clientes",
		description = "Obtiene una lista completa de todos los clientes registrados"
	)
	//@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@PreAuthorize("hasanyRole('ADMIN','USER')")
	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/clientes")
	public List<Cliente> index() {
		System.out.println("sdsds");
		return clienteService.findAll();
	}

	/*
	 * {
	 * "id": 1,
	 * "nombre": "Juan",
	 * "apellido": "Pérez",
	 * "email": "juan.perez@example.com",
	 * "createAt": "2024-12-06"
	 * 
	 * }
	 */

	@Operation(
		summary = "Crear cliente",
		description = "Crea un nuevo cliente con los datos proporcionados"
	)
	@PreAuthorize("hasRole('ADMIN')") //SOLO admin puede crear
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		cliente.setCreateAt(new Date());
		Map<String, Object> response = new HashMap<>();
		Cliente objCliente;

		if (result.hasErrors()) {

			List<String> listaErrores = new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() + "‘ " + error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {
			objCliente = this.clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la inserción en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Cliente>(objCliente, HttpStatus.OK);

	}

	@Operation(
		summary = "Listar cliente por ID",
		description = "Obtiene los detalles de un cliente específico utilizando su ID"
	)
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage() + "" + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: " + id + " no existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

	}

	@Operation(
		summary = "Actualizar cliente",
		description = "Actualiza los datos de un cliente existente utilizando su ID"
	)
	@PreAuthorize("hasRole('ADMIN')") // SOLO admin puede actualizar
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result,
			@PathVariable Integer id) {

		Cliente clienteActual = clienteService.findById(id);

		Cliente clienteUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> listaErrores = new ArrayList<>();

			for (FieldError error : result.getFieldErrors()) {
				listaErrores.add("El campo '" + error.getField() + "‘ " + error.getDefaultMessage());
			}

			response.put("errors", listaErrores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());

			clienteUpdated = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Operation(
		summary = "Eliminar cliente",
		description = "Elimina un cliente especifico utilizando su ID"
	)
	@PreAuthorize("hasRole('ADMIN')") // SOLO admin puede eliminar
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		Map<String, Object> response = new HashMap<>();

		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Operation(
		summary = "Listar regiones",
		description = "Obtiene una lista completa de todas las regiones, disponibles para los clientes"
	)
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones() {
		return clienteService.findAllRegiones();
	}

}
