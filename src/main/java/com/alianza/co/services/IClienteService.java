package com.alianza.co.services;

import org.springframework.http.ResponseEntity;

import com.alianza.co.Response.ClienteResponseRest;

public interface IClienteService {
	
	public ResponseEntity<ClienteResponseRest> search();
	public ResponseEntity<ClienteResponseRest> searchById(Long id);
	public ResponseEntity<ClienteResponseRest> search(String sharedkey);
	

}
