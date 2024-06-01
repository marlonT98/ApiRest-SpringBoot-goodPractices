package com.mt.model.dto;

import lombok.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@ToString
public class ClienteDto implements Serializable {

    private Integer idCliente;

    private String nombre;

    private String apellido;

    private String corre;

    private Date fechaRegistro;

}
