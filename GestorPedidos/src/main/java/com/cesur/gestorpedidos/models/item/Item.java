package com.cesur.gestorpedidos.models.item;

import com.cesur.gestorpedidos.models.pedido.Pedido;
import com.cesur.gestorpedidos.models.producto.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clase que representa un Item de la base de datos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="codigoPedido",referencedColumnName = "codigo")
    private Pedido pedido;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="idProducto")
    private Producto producto;


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", pedido=" + pedido.getCodigo() +
                ", cantidad=" + cantidad +
                ", producto=" + producto.getNombre() +
                '}';
    }
}
