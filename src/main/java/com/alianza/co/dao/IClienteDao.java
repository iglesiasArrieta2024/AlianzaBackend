package com.alianza.co.dao;

import org.springframework.data.repository.CrudRepository;

import com.alianza.co.model.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long> {

}
