package com.cesur.gestorpedidos.models.pedido;

import com.cesur.gestorpedidos.models.item.Item;
import com.cesur.gestorpedidos.models.producto.Producto;
import com.cesur.gestorpedidos.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un pedido de la base de datos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private String fecha;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    private Integer total;

    //@OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER)
    // TODO private List<Item> items;




}
