package com.mt.service;

import com.mt.model.dto.ClienteDto;
import com.mt.model.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listAll();

    Cliente save(ClienteDto cliente );

    Cliente findById( Integer id );

    void delete( Cliente cliente);

    boolean existsById(Integer id);




}
