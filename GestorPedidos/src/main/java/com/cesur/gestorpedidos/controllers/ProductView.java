package com.cesur.gestorpedidos.controllers;

import com.cesur.gestorpedidos.App;
import com.cesur.gestorpedidos.Session;
import com.cesur.gestorpedidos.models.item.Item;
import com.cesur.gestorpedidos.models.item.ItemDAOImp;
import com.cesur.gestorpedidos.models.pedido.PedidoDAOImp;
import com.cesur.gestorpedidos.models.producto.Producto;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.itextpdf.text.Document;
import javafx.util.StringConverter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductView implements Initializable {

    private ObservableList<Item> observableItems;
    ItemDAOImp itemDAOImp = new ItemDAOImp();
    PedidoDAOImp pedidoDAO = new PedidoDAOImp();

    @javafx.fxml.FXML
    private TableView<Item> tablaProductos;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<Item, Double> cPrecio;
    @javafx.fxml.FXML
    private TableColumn<Item, Integer> cCantidad;

    @javafx.fxml.FXML
    private TableColumn<Item, Double> cTotal;
    @javafx.fxml.FXML
    private Label txtPedido;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private MenuItem acercaDe;
    @javafx.fxml.FXML
    private MenuItem logOut;
    @javafx.fxml.FXML
    private MenuItem pdf;
    @javafx.fxml.FXML
    private ComboBox<Item> comboProductos;
    @javafx.fxml.FXML
    private Button btnGuardar;
    @javafx.fxml.FXML
    private Spinner<Integer> spinnerCantidad;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(comboProductos.getValue()!=null) {
            spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, comboProductos.getValue().getProducto().getCantidad(), 1, 1));
        }

        txtPedido.setText("Pedido:"+"\n"+Session.getPedidoactual().getCodigo());

        // Crear una ObservableList segura utilizando FXCollections
        observableItems = FXCollections.observableArrayList(Session.getPedidoactual().getItems());

        cNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getProducto().getNombre()));

        cPrecio.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getProducto().getPrecio()));

        cCantidad.setCellValueFactory(fila -> new SimpleObjectProperty<>(fila.getValue().getCantidad()));

        cTotal.setCellValueFactory(fila -> new SimpleObjectProperty<>((fila.getValue().getCantidad() * fila.getValue().getProducto().getPrecio())));

        tablaProductos.getItems().addAll(observableItems);


        //Mapeo del combo para que utilice el nombre de los productos
        comboProductos.setConverter(new StringConverter<Item>() {
            @Override
            public String toString(Item item) {

                if(item!= null){

                    return item.getProducto().getNombre();
                }else{

                    return null;
                }



            }

            @Override
            public Item fromString(String s) {
                return null;
            }
        });

        comboProductos.getItems().addAll(itemDAOImp.allItems());




        //Listener a la propiedad del combo para cambiar los datos mostrados en la tabla segun lo seleccionado
        comboProductos.valueProperty().addListener((observableValue, s, t1) -> {

            spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,comboProductos.getValue().getProducto().getCantidad(), 1,1));

            Item it = new Item();

            it.setCantidad(spinnerCantidad.getValue()); //Poner otro combo para setear la cantidad
            it.setPedido(Session.getPedidoactual());
            it.setProducto(t1.getProducto());
            Session.getPedidoactual().setTotal((int) (Session.getPedidoactual().getTotal()+it.getProducto().getPrecio()));
            Session.getPedidoactual().getItems().add(it);

            tablaProductos.getItems().add(it);


        });



    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {

        App.loadFXML("principal-view.fxml");

    }

    @javafx.fxml.FXML
    public void mostrarAcercaDe(ActionEvent actionEvent) {

        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informacion de la Aplicacion");
        alert.setContentText("Creado por Samuel Leiva Álvarez con todo el cariño del mundo para Francisco");
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) {

        App.loadFXML("login.fxml");
        Session.setUsuarioLogueado(null);
        Session.setPedidoactual(null);
    }

    @javafx.fxml.FXML
    public void exportarPDF(ActionEvent actionEvent) {
        try (FileOutputStream fileOut = new FileOutputStream("TablaProductos.pdf")) {
            Document document = new Document();
            PdfWriter.getInstance( document, fileOut);
            (document).open();

            // Crear una tabla en el documento PDF
            PdfPTable pdfTable = new PdfPTable(4);
            pdfTable.addCell("Nombre");
            pdfTable.addCell("Precio");
            pdfTable.addCell("Cantidad");
            pdfTable.addCell("Total");

            observableItems.forEach(item -> {
                pdfTable.addCell(item.getProducto().getNombre()); // Nombre
                pdfTable.addCell(String.valueOf(item.getProducto().getPrecio())); // Precio
                pdfTable.addCell(String.valueOf(item.getCantidad())); // Cantidad
                pdfTable.addCell(String.valueOf(item.getCantidad() * item.getProducto().getPrecio())); // Total
            });

            document.add(pdfTable);
            document.close();
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void saveItems(ActionEvent actionEvent) {

        pedidoDAO.actualizarPedido(Session.getPedidoactual());
        Session.setPedidoactual(null);
        App.loadFXML("principal-view.fxml");
    }
}