package com.cesur.gestorpedidos.models.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clase que representa un producto de la base de datos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {
    private Integer id;
    private String nombre;
    private Double precio;
    private Integer cantidad;
}
