package com.mt.service.impl;

import com.mt.model.dao.ClienteDao;
import com.mt.model.dto.ClienteDto;
import com.mt.model.entity.Cliente;
import com.mt.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImplService implements IClienteService {


    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> listAll() {
        return (List) clienteDao.findAll();
    }

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        //pasando al cliente los datos que tiene el clienteDto
        Cliente cliente = Cliente.builder()
                .idCliente( clienteDto.getIdCliente() )
                .nombre( clienteDto.getNombre() )
                .apellido( clienteDto.getApellido() )
                .corre( clienteDto.getCorre() )
                .fechaRegistro( clienteDto.getFechaRegistro() )
                .build();

        return clienteDao.save( cliente );
    }

    //para la operacion de busqueda o recuperacion  para
    //asegurarnos de que solo  podemos realizar la operacion de solo lectura
    @Transactional( readOnly = true)
    @Override
    public Cliente findById(Integer id) {
                       //en caso no se encuentre es null
        return clienteDao.findById( id ).orElse(null);
    }

    //indicar que las operaciones realizadas dentro
    // de ese método o clase deben ser ejecutadas dentro de una transacción.
    @Transactional
    @Override
    public void delete(Cliente cliente) {

        clienteDao.delete( cliente );

    }

    @Override
    public boolean existsById(Integer id) {
        return clienteDao.existsById(id);
    }
}
