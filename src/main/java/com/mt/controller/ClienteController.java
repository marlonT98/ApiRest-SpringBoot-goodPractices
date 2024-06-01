package com.mt.controller;

import com.mt.model.dto.ClienteDto;
import com.mt.model.entity.Cliente;
import com.mt.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//se aplica a una clase para marcarla como un controlador de solicitudes.
//la anotacion Spring rectController se usa para crear servicios web restFull usando spring mvc
@RestController
@RequestMapping("/api/v1")
public class ClienteController {


    //llamando a nuestro servicio
    @Autowired
    private ICliente clienteService;


    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    //el request body lo transforma a un objeto cliente ( se pasa por json y el lo convierte a un cliente)
    public ClienteDto create( @RequestBody ClienteDto clienteDto) {

        //al guardar el cliente este nos devuelve el cliente guardado
        Cliente clienteSave = clienteService.save(clienteDto);

        //retornamos el clienteDto con los campos de el cliente que se guardo
        return  ClienteDto.builder()
                .idCliente( clienteSave.getIdCliente() )
                .nombre( clienteSave.getNombre() )
                .apellido( clienteSave.getApellido() )
                .corre( clienteSave.getCorre() )
                .fechaRegistro( clienteSave.getFechaRegistro() )
                .build();

    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto update(  @RequestBody  ClienteDto clientDto) {
        //al guardar el cliente este nos devuelve el cliente guardado
        Cliente clienteUpdate = clienteService.save(clientDto);

        //retornamos el clienteDto con los campos actualizados
        return  ClienteDto.builder()
                .idCliente( clienteUpdate.getIdCliente() )
                .nombre( clienteUpdate.getNombre() )
                .apellido( clienteUpdate.getApellido() )
                .corre( clienteUpdate.getCorre() )
                .fechaRegistro( clienteUpdate.getFechaRegistro() )
                .build();
    }


    @DeleteMapping("cliente/{id}")
    //especifica que es unavariable en forma de parametro
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Map< String , Object > response = new HashMap<>();

        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);

            return new ResponseEntity<>(clienteDelete , HttpStatus.NO_CONTENT);

        }catch ( DataAccessException exDt){
            //me enviara un mapa de errores

            response.put( "mensaje",exDt.getMessage() );
            response.put( "cliente",null);

            return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);


        }

    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto showById( @PathVariable  Integer id) {

        //al guardar el cliente este nos devuelve el cliente guardado
        Cliente cliente = clienteService.findById(id);

        //retornamos el clienteDto con los campos actualizados del cliente de la bdd
        return  ClienteDto.builder()
                .idCliente( cliente.getIdCliente() )
                .nombre( cliente.getNombre() )
                .apellido( cliente.getApellido() )
                .corre( cliente.getCorre() )
                .fechaRegistro( cliente.getFechaRegistro() )
                .build();

    }


}
