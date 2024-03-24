package com.alianza.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.co.Response.ClienteResponseRest;
import com.alianza.co.services.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteservice;
	
	@GetMapping("/clientes")
	public ResponseEntity<ClienteResponseRest> serachCliente(){
		ResponseEntity<ClienteResponseRest> reponse = clienteservice.search();
		return reponse;
		
	}

}
