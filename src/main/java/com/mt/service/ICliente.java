package com.mt.service;

import com.mt.model.dto.ClienteDto;
import com.mt.model.entity.Cliente;

public interface ICliente {

    Cliente save(ClienteDto cliente );

    Cliente findById( Integer id );

    void delete( Cliente cliente);




}
