package com.alianza.co.services;

import org.springframework.http.ResponseEntity;

import com.alianza.co.Response.ClienteResponseRest;
import com.alianza.co.model.Cliente;

public interface IClienteService {
	
	public ResponseEntity<ClienteResponseRest> search();
	public ResponseEntity<ClienteResponseRest> searchById(Long id);
	public ResponseEntity<ClienteResponseRest> save(Cliente cliente);
	public ResponseEntity<ClienteResponseRest> update(Cliente cliente, Long id);
	

}
