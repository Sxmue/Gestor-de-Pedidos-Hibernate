package com.cesur.gestorpedidos.models.pedido;

import com.cesur.gestorpedidos.Session;
import com.cesur.gestorpedidos.models.item.Item;
import com.cesur.gestorpedidos.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un pedido de la base de datos
 */
@Data
@AllArgsConstructor
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

    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>() ;

    @Transient
    private String fechaFormateada;

    public Pedido() {

        //Inicializacion de la fecha, cantidad ,usuario y codigo de pedido por defecto
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        fechaFormateada = currentDate.format(formatter);

        this.setFecha(fechaFormateada);

        this.setUsuario(Session.getUsuarioLogueado());

        this.setCodigo("PED-");

        this.setTotal(0);


    }

    public static void merge(Pedido viejo, Pedido nuevo){
        viejo.setId(nuevo.getId());
        viejo.setFecha(nuevo.getFecha());
        viejo.setCodigo(nuevo.getCodigo());
        viejo.setUsuario(nuevo.getUsuario());
        viejo.setItems(nuevo.getItems());
        viejo.setTotal(nuevo.getTotal());

    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", usuario=" + usuario.getNombre() +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}