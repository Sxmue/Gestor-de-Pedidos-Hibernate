package com.cesur.gestorpedidos.models.item;

import com.cesur.gestorpedidos.Session;
import com.cesur.gestorpedidos.database.HibernateUtil;
import com.cesur.gestorpedidos.models.pedido.Pedido;
import com.cesur.gestorpedidos.models.producto.Producto;
import com.cesur.gestorpedidos.models.producto.ProductoDAOImp;
import com.cesur.gestorpedidos.models.usuario.Usuario;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

            Transaction t = s.beginTransaction();

            Query<Item> q = s.createQuery("from Item ", Item.class);

            items = (ArrayList<Item>) q.getResultList();


            t.commit();
        }
        return items;
    }

    @Override
    public void addItem(Item it){

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

                s.persist(it);


            t.commit();
        }

    }

    @Override
    public void delete(Item it){

        try (org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()) {

            Transaction t = s.beginTransaction();

            s.remove(it);

            t.commit();
        }

    }


}

