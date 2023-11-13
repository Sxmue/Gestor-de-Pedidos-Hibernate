module com.cesur.gestortareas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cesur.gestorpedidos to javafx.fxml;
    exports com.cesur.gestorpedidos;
}