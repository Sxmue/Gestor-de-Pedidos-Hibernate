package com.cesur.gestorpedidos.models.producto;


import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementacion del DAO producto
 */
public class ProductoDAOImp implements ProductoDAO{

    /* Log para trazar la clase */
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProductoDAOImp.class);

    /*Conexion a la base de datos que va a recibir de la clase Database*/
    private final Connection connection;


    //Constantes para las Querys
    private static final String QUERY_LOAD_BY_ID="SELECT * FROM producto WHERE id=?";

    /**
     * Constructor que almacena la conexion en la variable c para trabajar con ella
     * @param c Conexion que viene de la clase Database
     */
    public ProductoDAOImp(Connection c){
        this.connection=c;

    }

    /**
     * Metodo para leer un producto a traves de su ID
     * @param id id del producto
     * @return el producto leido de la base de datos
     */
    @Override
    public Producto load(Integer id) {
        Producto result = null;

        try(PreparedStatement pst=connection.prepareStatement(QUERY_LOAD_BY_ID)){
            LOG.info(QUERY_LOAD_BY_ID);

            pst.setString(1, String.valueOf(id));

            ResultSet rs = pst.executeQuery();

            while (rs.next()){

                LOG.info("Producto leido correctamente");

                result = new Producto();

                result.setId(rs.getInt("id"));
                result.setNombre(rs.getString("nombre"));
                result.setPrecio(rs.getDouble("precio"));
                result.setCantidad(rs.getInt("cantidad"));


            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
