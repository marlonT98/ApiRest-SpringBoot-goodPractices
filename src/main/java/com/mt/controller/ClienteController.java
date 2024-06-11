package com.mt.controller;

import com.mt.model.dto.ClienteDto;
import com.mt.model.entity.Cliente;
import com.mt.model.payload.MesajeResponse;
import com.mt.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//se aplica a una clase para marcarla como un controlador de solicitudes.
//la anotacion Spring rectController se usa para crear servicios web restFull usando spring mvc
@RestController
@RequestMapping("/api/v1")
public class ClienteController {


    //llamando a nuestro servicio
    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll() {

     List<Cliente> clientes = clienteService.listAll();

        if (clientes.isEmpty()) {//si getList es nulo

            return new ResponseEntity<>(
                    MesajeResponse.builder()
                            .mensaje("NO Existe registros")
                            .object(null)
                            .build(),
                    HttpStatus.OK
            );

        }


        return new ResponseEntity<>(
                MesajeResponse.builder()
                        .mensaje("clientes encontrados")
                        .object(clientes)
                        .build()
               , HttpStatus.OK
        );

    }




    @PostMapping("cliente")
    //el request body lo transforma a un objeto cliente ( se pasa por json y el lo convierte a un cliente)
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        //al guardar el cliente este nos devuelve el cliente guardado
        Cliente clienteSave = null;
        try {
            //cuando se registre
            clienteSave = clienteService.save(clienteDto);

            //retornamos el clienteDto con los campos de el cliente que se guardo
            clienteDto = ClienteDto.builder()
                    .idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .corre(clienteSave.getCorre())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();

            return new ResponseEntity<>(
                    MesajeResponse.builder()
                            .mensaje("Guardado correctamente")
                            .object(clienteDto).build(), HttpStatus.CREATED
            );


        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(
                    MesajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null).build(),
                    HttpStatus.METHOD_NOT_ALLOWED
            );

        }


    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clientDto, @PathVariable Integer id) {

        Cliente clienteUpdate = null;

        try {



            if (clienteService.existsById(id)) {//si lo encontro (si es true lo registra - si es false lo elimina)

                //por si es que el cliente envia otro id diferente a la hora de actualizar
                //el id que se esta pasando por parametro se enviara al clientDto
                clientDto.setIdCliente( id );
                //que me actualize
                clienteUpdate = clienteService.save(clientDto);

                //mostrando el cliente actualizado
                return new ResponseEntity<>(
                        MesajeResponse.builder()
                                .mensaje("Actualizado correctamente")
                                .object(ClienteDto.builder()
                                        .idCliente(clienteUpdate.getIdCliente())
                                        .nombre(clienteUpdate.getNombre())
                                        .apellido(clienteUpdate.getApellido())
                                        .corre(clienteUpdate.getCorre())
                                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                                        .build()).build(), HttpStatus.CREATED
                );

            } else {
                return new ResponseEntity<>(
                        MesajeResponse.builder()
                                .mensaje("El registro  que intenta actualizar no se encuentra en la bdd")
                                .object(null).build(),
                        HttpStatus.NOT_FOUND
                );

            }


        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(
                    MesajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null).build(),
                    HttpStatus.NOT_FOUND
            );

        }
    }


    @DeleteMapping("cliente/{id}")
    //especifica que es unavariable en forma de parametro
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);

            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt) {

            return new ResponseEntity<>(
                    MesajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null).build(),
                    HttpStatus.METHOD_NOT_ALLOWED
            );


        }

    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {

        //al guardar el cliente este nos devuelve el cliente guardado
        Cliente cliente = clienteService.findById(id);

        if (cliente == null) {

            return new ResponseEntity<>(
                    MesajeResponse.builder()
                            .mensaje("El registro que intenta buscar no existe")
                            .object(null).build(),
                    HttpStatus.NOT_FOUND
            );

        }


        return new ResponseEntity<>(
                MesajeResponse.builder()
                        .mensaje("Consulta exitosa")
                        .object(
                                ClienteDto.builder()
                                        .idCliente(cliente.getIdCliente())
                                        .nombre(cliente.getNombre())
                                        .apellido(cliente.getApellido())
                                        .corre(cliente.getCorre())
                                        .fechaRegistro(cliente.getFechaRegistro())
                                        .build()).build(),
                HttpStatus.OK
        );

    }


}
