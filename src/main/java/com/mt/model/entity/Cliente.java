package com.mt.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "correo")
    private String corre;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

}
