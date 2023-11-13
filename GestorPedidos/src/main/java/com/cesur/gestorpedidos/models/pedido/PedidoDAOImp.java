package com.cesur.gestorpedidos.models.pedido;

import com.cesur.gestorpedidos.database.HibernateUtil;
import com.cesur.gestorpedidos.models.item.Item;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


/**
 * Implementacion del DAO pedido
 */
public class PedidoDAOImp implements PedidoDAO {
    /* Log para trazar la clase */
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PedidoDAOImp.class);


    @Override
    public void deletePedido(Pedido p) {

        //Lambda para acceder a la base de datos
        HibernateUtil.getSessionFactory().inTransaction(session -> {

            //Traemos el objeto a borrar para que sea persistente
            Pedido pd = session.get(Pedido.class,p.getId());

            //Lo eliminamos
            session.remove(pd);

        });



    }
}
