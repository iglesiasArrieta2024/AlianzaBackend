package com.alianza.co.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alianza.co.Response.ClienteResponseRest;

import com.alianza.co.dao.IClienteDao;
import com.alianza.co.model.Cliente;

@Service

public class ClienteResponseServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ClienteResponseRest> search() {
		ClienteResponseRest reponserest = new ClienteResponseRest();

		try {
			List<Cliente> cliente = (List<Cliente>) clienteDao.findAll();
			reponserest.getClienteResponse().setClientes(cliente);
			reponserest.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
		} catch (Exception e) {
			reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida al consultar");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ClienteResponseRest> searchById(Long id) {
		ClienteResponseRest reponserest = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();

		try {
			Optional<Cliente> cliente = clienteDao.findById(id);
			if (cliente.isPresent()) {
				list.add(cliente.get());
				reponserest.getClienteResponse().setClientes(list);
				reponserest.setMetadata("Respuesta Exitosa", "00", "Transaccion exitosa");
			} else {
				reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida Cliente no existe");
				return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida al consultar por Id");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.OK);
	}

	

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> save(Cliente cliente) {
		ClienteResponseRest reponserest = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();

		try {
			Cliente clientesave = clienteDao.save(cliente);
			if (clientesave != null) {
				list.add(clientesave);
				reponserest.getClienteResponse().setClientes(list);
				reponserest.setMetadata("Respuesta OK", "00", "Respuesta al guardar cliente");

			} else {
				reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida al guardar cliente");
				return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida al guardar cliente");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> update(Cliente cliente, Long id) {
		ClienteResponseRest reponserest = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();

		try {
			Optional<Cliente> clienteSearch = clienteDao.findById(id);
			if (clienteSearch.isPresent()) {
				clienteSearch.get().setBusiness_Id(cliente.getBusiness_Id());
				clienteSearch.get().setSharedKey(cliente.getSharedKey());
				;
				clienteSearch.get().setEmail(cliente.getEmail());
				clienteSearch.get().setPhone(cliente.getPhone());
				clienteSearch.get().setData_added(cliente.getData_added());

				Cliente clienteUpdate = clienteDao.save(clienteSearch.get());

				if (clienteUpdate != null) {
					list.add(clienteUpdate);
					reponserest.getClienteResponse().setClientes(list);
					reponserest.setMetadata("Respuesta OK", "00", "Respuesta al Actualizar cliente");
				} else {
					reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida al actualizar cliente");
					return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.BAD_REQUEST);

				}
			} else {
				reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida al actualizar cliente");
				return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			reponserest.setMetadata("Respuesta Fallida", "-1", "Respuesta fallida al acutalizar cliente");
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ClienteResponseRest>(reponserest, HttpStatus.OK);
	}

}
