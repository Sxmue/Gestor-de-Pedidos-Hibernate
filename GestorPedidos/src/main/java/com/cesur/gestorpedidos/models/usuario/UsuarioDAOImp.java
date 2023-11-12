package com.cesur.gestorpedidos.models.usuario;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import com.cesur.gestorpedidos.database.HibernateUtil;


/**
 * Implementacion del DAO Usuario
 */
public class UsuarioDAOImp implements UsuarioDAO {

    /* Log para trazar la clase */
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(UsuarioDAOImp.class);


    /**
     * Metodo para leer un Usuario de la base de datos
     * @param nombre nombre del usuario
     * @param pass contraseña del usuario
     * @return el usuario de la base de datos
     */
    @Override
    public Usuario load(String nombre, String pass) {

        Usuario u = null;

        try(Session s = HibernateUtil.getSessionFactory().openSession()){

            Query<Usuario> q = s.createQuery("FROM Usuario where nombre=:n and pass=:p",Usuario.class);

            q.setParameter("n",nombre);
            q.setParameter("p",pass);

            try {

                u = q.getSingleResult();

            }catch(Exception e){
                System.out.println(e.getMessage());
            }


        }catch (Exception e){

            LOG.error("Algo ha ido mal en la session");

            System.out.println(e.getMessage());

        }



      return u;
    }


}
