package com.mt.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class MesajeResponse  implements Serializable {

    private String mensaje;

    private Object object;





}
