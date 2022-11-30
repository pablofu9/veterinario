package modelo;

import javafx.scene.control.Alert;

public class Alertas {

    public static void crearAlertaError(String textoAlerta) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(textoAlerta);
        alert.showAndWait();
    }

    public static void crearAlertaInfo(String textoAlerta) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText(textoAlerta);
        alert.showAndWait();
    }
}
