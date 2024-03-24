package com.alianza.co.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alianza.co.Response.ClienteResponseRest;

import com.alianza.co.dao.IClienteDao;
import com.alianza.co.model.Cliente;



@Service
@Transactional(readOnly = true)
public class ClienteResponseServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	public ResponseEntity<ClienteResponseRest> search() {
		  ClienteResponseRest reponserest = new ClienteResponseRest();
		  
		  try {
			List<Cliente> cliente = (List<Cliente>) clienteDao.findAll();
			reponserest.getClienteResponse().setClientes(cliente);
			reponserest.setMetadata("Respuesta Ok","00", "Respuesta Exitosa");
		} catch (Exception e) {
			reponserest.setMetadata("Respuesta Fallida","-1", "Respuesta fallida al consultar");
			e.getStackTrace();
			return new  ResponseEntity<ClienteResponseRest>(reponserest,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new  ResponseEntity<ClienteResponseRest>(reponserest,HttpStatus.OK);
	}

}
