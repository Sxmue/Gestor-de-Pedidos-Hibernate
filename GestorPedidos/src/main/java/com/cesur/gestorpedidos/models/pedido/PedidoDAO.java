package com.cesur.gestorpedidos.models.pedido;

import com.cesur.gestorpedidos.models.item.Item;
import com.cesur.gestorpedidos.models.usuario.Usuario;

import java.util.ArrayList;

/**
 * Interfaz para definir el DAO de pedido
 */
public interface PedidoDAO {

    public void deletePedido(Pedido p);

}
