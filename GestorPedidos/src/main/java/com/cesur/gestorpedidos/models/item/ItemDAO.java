package com.cesur.gestorpedidos.models.item;

import com.cesur.gestorpedidos.models.pedido.Pedido;
import com.cesur.gestorpedidos.models.producto.Producto;

import java.util.ArrayList;

/**
 * Interfaz para definir el DAO de Item
 */
public interface ItemDAO {

    public ArrayList<Item> allItems();


}
