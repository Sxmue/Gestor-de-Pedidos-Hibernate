package com.cesur.gestorpedidos.models.item;

import com.cesur.gestorpedidos.database.HibernateUtil;
import com.cesur.gestorpedidos.models.pedido.Pedido;
import com.cesur.gestorpedidos.models.producto.Producto;
import com.cesur.gestorpedidos.models.producto.ProductoDAOImp;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Implementacion del DAO Item
 */
public class ItemDAOImp implements ItemDAO {

    /* Log para trazar la clase */
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ItemDAOImp.class);


    @Override
    public ArrayList<Item> allItems() {
        ArrayList<Item> items = new ArrayList<>();

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {
            //Empezamos transaccion ya que vamos a escribir en la bbdd
            Transaction t = s.beginTransaction();

            Query<Item> q = s.createQuery("from Item ", Item.class);

            items = (ArrayList<Item>) q.getResultList();

            //Ejecutamos el commit y automaticamente se actualiza la base de datos
            t.commit();
        }
        return items;
    }
}

