package com.cesur.gestorpedidos.models.producto;


import com.cesur.gestorpedidos.database.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementacion del DAO producto
 */
public class ProductoDAOImp implements ProductoDAO{

    /* Log para trazar la clase */
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProductoDAOImp.class);

}
