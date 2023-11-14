package com.cesur.gestorpedidos.models.pedido;

import com.cesur.gestorpedidos.Session;
import com.cesur.gestorpedidos.database.HibernateUtil;
import com.cesur.gestorpedidos.models.item.Item;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * Implementacion del DAO pedido
 */
public class PedidoDAOImp implements PedidoDAO {
    /* Log para trazar la clase */
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PedidoDAOImp.class);


    @Override
    public void borrarPedido(Pedido p) {

            //Lambda para acceder a la base de datos
            HibernateUtil.getSessionFactory().inTransaction(session -> {

                //Traemos el objeto a borrar para que sea persistente
                Pedido pd = session.get(Pedido.class, p.getId());

                //Lo eliminamos
                session.remove(pd);

            });

    }

    @Override
    public Pedido guardarPedido(Pedido p) {

        Pedido pSave = null;

        try(org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();


            s.persist(p);

            p.setCodigo(p.getCodigo()+p.getId());


            pSave=p;

            t.commit();



        }catch (Exception e){

            System.out.println(e.getMessage());
            System.out.println("Error al intentar persistir el pedido. Detalles del pedido:");
            System.out.println("ID del Pedido: " + p.getId()); // Asegúrate de que el ID se haya asignado correctamente
            System.out.println("Otros detalles del Pedido: " + p); // Puedes imprimir otros detalles relevantes del Pedido

        }

        return pSave;

    }

    @Override
    public void actualizarPedido(Pedido p) {


        try(org.hibernate.Session s = HibernateUtil.getSessionFactory().openSession()){

            Transaction t = s.beginTransaction();

            Pedido update = s.get(Pedido.class, Session.getPedidoactual().getId());

            Pedido.merge(update, p);

            //Ejecutamos el commit y automaticamente se actualiza la base de datos
            t.commit();



        }
    }
}
