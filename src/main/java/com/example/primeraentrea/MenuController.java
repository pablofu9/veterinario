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
    private JFXTextField txtId, txtNombre,txtRaza,txtPeso,txtFiltro;

    @FXML
    private JFXListView <Perros> lista;

    @FXML
    private JFXButton btnInsertar, btnLimpiar, btnModificar,btnRestablecer,btnEliminar;


    //METODO QUE INSERTAR SI LOS CAMPOS ESTAN RELLENOS
    @FXML
    private void insertar(){

        Perros p1 = new Perros(txtNombre.getText(), txtRaza.getText(), Integer.parseInt(txtPeso.getText()));
        if(txtNombre.getText().length()<1 || txtRaza.getText().length()<1|| txtPeso.getText().length()<1){
            Alertas.crearAlertaError("Rellena todos los campos");
        }else{
            conexion.CRUD_Perro.insertarPerro(p1);
            Alertas.crearAlertaInfo("Perro insertado");
            cargarLista();
        }
    }

    //METODO PARA CARGAR EL LISTVIEW
    public void cargarLista(){
        try{
            List<Perros> perros = CRUD_Perro.getPerros();
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
        txtId.setText("");
        txtNombre.setText("");
        txtRaza.setText("");
        txtPeso.setText("");
        txtFiltro.setText("");
    }


    @FXML
    private void modificar(){
        if(txtId.getText().isEmpty()){
            Alertas.crearAlertaError("Debes seleccionar un perro en la tabla...");
        }else{
            Perros p1 = new Perros(Integer.parseInt(txtId.getText()), txtNombre.getText(), txtRaza.getText(), Integer.parseInt(txtPeso.getText()));
            CRUD_Perro.modificarPerro(p1);
            cargarLista();
            limpiarCampos();
        }
    }
    //Boton para filtrar segun el campo de texto y lo que escribas
    @FXML
    private void filtrar(){
       List<Perros> perrosFilter=CRUD_Perro.getPerrosFiltro(txtFiltro.getText().toString());
        lista.setItems(FXCollections.observableList(perrosFilter));
    }

    //Boton para restablecer la tabla despues de filtrar
    @FXML
    private void restablecer(){
        cargarLista();
        limpiarCampos();
    }

    @FXML
    private void eliminar(){
        Perros p1 =lista.getSelectionModel().getSelectedItem();
        int idBorrar = p1.getId();
        CRUD_Perro.eliminarPerro(idBorrar);
        limpiarCampos();
        cargarLista();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarLista();
    }
}
