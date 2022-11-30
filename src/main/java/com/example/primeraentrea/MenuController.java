package com.example.primeraentrea;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import conexion.CRUD_Perro;
import conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Alertas;
import modelo.Perros;
import org.w3c.dom.events.Event;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private JFXTextField txtId, txtNombre,txtRaza,txtPeso;

    @FXML
    private JFXListView <Perros> lista;

    @FXML
    private JFXButton btnInsertar, btnLimpiar, btnAnadir, btnModificar;


    @FXML
    private void insertar(){

        Perros p1 = new Perros(txtNombre.getText(), txtRaza.getText(), Integer.parseInt(txtPeso.getText()));
        if(txtNombre.getText().isEmpty() || txtRaza.getText().isEmpty() || txtPeso.getText().isEmpty()){
            Alertas.crearAlertaError("Rellena todos los campos");
        }else{
            conexion.CRUD_Perro.insertarPerro(p1);
            Alertas.crearAlertaInfo("Perro insertado");
        }
    }

    public void cargarLista(){
        try{
            List<Perros> perros = CRUD_Perro.getVehiculos();
            lista.setItems(FXCollections.observableList(perros));
        }catch (Exception e){
            Alertas.crearAlertaError("No se puede cargar la lista");
        }
    }

    private void cargarPerro(Perros perro){
        txtId.setText(String.valueOf(perro.getId()));
        txtNombre.setText(perro.getNombre());
        txtRaza.setText(perro.getRaza());
        txtPeso.setText(String.valueOf(perro.getPeso()));
    }
    @FXML
    public void seleccionarPerro(){
        Perros p1 =lista.getSelectionModel().getSelectedItem();
        cargarPerro(p1);
    }

    @FXML
    private void limpiarCampos(){
        txtId.setText(" ");
        txtNombre.setText(" ");
        txtRaza.setText(" ");
        txtPeso.setText(" ");
    }

    @FXML
    private void anadir(){

    }

    @FXML
    private void modificar(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarLista();
    }
}
