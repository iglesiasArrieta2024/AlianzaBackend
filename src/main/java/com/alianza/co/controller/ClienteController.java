package com.alianza.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alianza.co.Response.ClienteResponseRest;
import com.alianza.co.model.Cliente;
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
		@GetMapping("/clientes{id}")
		public ResponseEntity<ClienteResponseRest> serachClienteById(@PathVariable Long id){
			ResponseEntity<ClienteResponseRest> reponse = clienteservice.searchById(id);
			return reponse;
		
		
	}
		
		@GetMapping("/busqueda{sharedkey}")
		public ResponseEntity<ClienteResponseRest> searchSharedById(@PathVariable Long sharedkey ){
			ResponseEntity<ClienteResponseRest> reponse = clienteservice.searchById(sharedkey);
			return reponse;
		
		
	}
		
		@PostMapping("/save")
		public ResponseEntity<ClienteResponseRest> save(@RequestBody Cliente cliente){
			ResponseEntity<ClienteResponseRest> reponse = clienteservice.save(cliente);
			return reponse;
		
		
	}

}
