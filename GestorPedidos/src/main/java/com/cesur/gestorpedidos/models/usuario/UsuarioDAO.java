package com.cesur.gestorpedidos.models.usuario;


/**
 * Interfaz para definir el DAO de usuario
 */
public interface UsuarioDAO {

    public Usuario load(String nombre,String pass);
}
