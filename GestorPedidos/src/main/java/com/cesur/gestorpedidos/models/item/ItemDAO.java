package com.cesur.gestorpedidos.models.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz para definir el DAO de Item
 */
public interface ItemDAO {

    public ArrayList<Item> allItems();
    public void delete(Item it);
    public void addItem(Item it);


}
