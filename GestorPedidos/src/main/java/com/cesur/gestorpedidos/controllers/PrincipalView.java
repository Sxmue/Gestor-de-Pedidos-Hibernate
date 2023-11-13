package com.cesur.gestorpedidos.controllers;

import com.cesur.gestorpedidos.App;
import com.cesur.gestorpedidos.Session;
import com.cesur.gestorpedidos.models.pedido.Pedido;
import com.cesur.gestorpedidos.models.pedido.PedidoDAO;
import com.cesur.gestorpedidos.models.pedido.PedidoDAOImp;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.Event;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Clase que representa la ventana principal en nuestra aplicacion
 */
public class PrincipalView implements Initializable {

    /*Variable utilizada para la obtencion de los pedidos de la base de datos*/
    PedidoDAO pedidoDAO = new PedidoDAOImp();


    @javafx.fxml.FXML
    private TableView<Pedido> tablaPedidos;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cCodigo;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> cFecha;
    @javafx.fxml.FXML
    private TableColumn<Pedido, Integer> cTotal;
    @javafx.fxml.FXML
    private Label txtBienvenido;
    @javafx.fxml.FXML
    private MenuItem acercaDe;
    @javafx.fxml.FXML
    private MenuItem logOut;
    @javafx.fxml.FXML
    private ComboBox<String> comboFecha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Obtencion de todos los pedidos del usuario
        List<Pedido> pedidos = Session.getUsuarioLogueado().getPedidos();
        ObservableList<Pedido> observablePedidos = FXCollections.observableArrayList(pedidos);


        txtBienvenido.setText("Bienvenido" + "\n" + Session.getUsuarioLogueado().getNombre());


        /* Inicializacion de la tabla Pedidos*/
        inicializadorTabla(observablePedidos);



        /* Combobox para filtar por Fecha*/

        //Inicializacion del combobox
        inicializadorCombobox(observablePedidos);


        //Listener a la propiedad del combo para cambiar los datos mostrados en la tabla segun lo seleccionado
        comboFecha.valueProperty().addListener((observableValue, s, t1) -> {

            ObservableList<Pedido> observableFecha = FXCollections.observableArrayList(pedidos);
            if (!Objects.equals(t1, "Cualquiera")) {

                for (Pedido r : observablePedidos) {

                    if (!r.getFecha().contains(t1)) {
                        observableFecha.remove(r);

                    }

                }
                tablaPedidos.getItems().clear();
                tablaPedidos.getItems().addAll(observableFecha);
            } else {
                tablaPedidos.getItems().clear();
                tablaPedidos.getItems().addAll(observablePedidos);
            }

        });


    }

    /**
     * Metodo para mostrar la informacion del pedido
     * @param event Listener de la propiedad
     */
    @javafx.fxml.FXML
    public void pedidoInfo(Event event) {
        if (((MouseEvent) event).getClickCount() == 2 && (tablaPedidos.getSelectionModel().getSelectedItem() != null)) {

            Session.setPedidoactual(tablaPedidos.getSelectionModel().getSelectedItem());

            App.loadFXML("product-view.fxml");

        }
    }

    /**
     * Metodo para la inicializacion de la tabla pedidos
     *
     * @param observablePedidos observable con los pedidos del usuario
     */
    private void inicializadorTabla(ObservableList<Pedido> observablePedidos) {
        cCodigo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCodigo()));
        cFecha.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getFecha()));
        cTotal.setCellValueFactory(fila -> new SimpleObjectProperty<>((fila.getValue().getTotal())));
        tablaPedidos.getItems().addAll(observablePedidos);
    }

    /**
     * Metodo para la inicialización del combo box
     *
     * @param observablePedidos
     */
    private void inicializadorCombobox(ObservableList<Pedido> observablePedidos) {
        ArrayList<String> anhos = new ArrayList<>(0);
        anhos.add("Cualquiera");

        for (Pedido s : observablePedidos) {

            String[] d = s.getFecha().split("-");

            anhos.add(d[0]);
        }
        comboFecha.getItems().addAll(anhos);
    }

    /**
     * Metodo para mostrar el mensaje "Acerca de"
     *
     * @param actionEvent listener del evento
     */
    @javafx.fxml.FXML
    public void mostrarAcercaDe(ActionEvent actionEvent) {

        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informacion de la Aplicacion");
        alert.setContentText("Creado por Samuel Leiva Álvarez con todo el cariño del mundo para Francisco");
        alert.showAndWait();


    }

    /**
     * Metodo para hacer Logout
     *
     * @param actionEvent listener del evento
     */
    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {

        App.loadFXML("login.fxml");
        Session.setUsuarioLogueado(null);

    }
}