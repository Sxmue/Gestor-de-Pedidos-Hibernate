package com.cesur.gestorpedidos.controllers;

import com.cesur.gestorpedidos.App;
import com.cesur.gestorpedidos.Session;
import com.cesur.gestorpedidos.models.usuario.Usuario;
import com.cesur.gestorpedidos.models.usuario.UsuarioDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Login implements Initializable {
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Login.class);
    ArrayList<Usuario> users;

    private final UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

    @javafx.fxml.FXML
    private ImageView imgTop;
    @javafx.fxml.FXML
    private Label txtMarca;
    @javafx.fxml.FXML
    private ImageView userImg;
    @javafx.fxml.FXML
    private TextField txtUser;
    @javafx.fxml.FXML
    private ImageView imgPass;
    @javafx.fxml.FXML
    private PasswordField txtPass;
    @javafx.fxml.FXML
    private Button btnLogin;
    @javafx.fxml.FXML
    private MenuItem acercaDe;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        LOG.info("Ventana de Login cargada correctamente");

    }

    @javafx.fxml.FXML
    public void login(ActionEvent actionEvent) {

        String name = txtUser.getText();
        String pass = txtPass.getText();

        Usuario u = usuarioDAO.load(name, pass);

        if (!name.isEmpty() && !pass.isEmpty()) {
            LOG.info("Datos validos");

            if (u!=null) {

                LOG.info("Usuario Valido");

                Session.setUsuarioLogueado(u);
                App.loadFXML("principal-view.fxml");
                LOG.info("Usuario logueado");

            } else {

                LOG.error("Usuario no valido");

                var alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setContentText("usuario o contraseña incorrectos");
                alert.showAndWait();

            }

        } else {

            LOG.error("Datos no correctos");
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Introducir datos validos por favor");
            alert.showAndWait();

        }


    }


    @javafx.fxml.FXML
    public void mostrarAcercaDe(ActionEvent actionEvent) {

        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informacion de la Aplicacion");
        alert.setContentText("Creado por Samuel Leiva Álvarez con todo el cariño del mundo para Francisco");
        alert.showAndWait();

    }
}
