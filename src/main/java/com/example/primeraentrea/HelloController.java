package com.example.primeraentrea;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import conexion.CRUD_Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Alertas;

import java.io.IOException;

public class HelloController {

    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXTextField txtPass;

    @FXML
    private JFXButton btnLogin;

    //Boton de login, usa el crud usuarios para comprobar si el login es correcto
    //PARA LOGEARSE: juan-juan
    @FXML
    private void login() throws IOException {
        String contrasena;
        if(txtUser.getText().isEmpty() || txtPass.getText().isEmpty()){
            Alertas.crearAlertaError("Rellena todos los campos");
        }else{
            contrasena= CRUD_Usuario.buscarUser(txtUser.getText());
            if(contrasena!=null){
                if(contrasena.equals(txtPass.getText())){
                    Alertas.crearAlertaInfo("Bienvenido "+txtUser.getText());
                    Stage stage = new Stage();

                    Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                    Scene scene = new Scene(root);
                    stage = new Stage(StageStyle.DECORATED);
                    stage.setScene(scene);
                    stage.show();
                    //Para cerrar el login
                    Stage loginStage = (Stage) this.btnLogin.getScene().getWindow();
                    loginStage.close();
                }else{
                    Alertas.crearAlertaError("Contraseña incorrecta");
                }
            }else{
                Alertas.crearAlertaError("Credenciales incorrectas");
            }
        }
    }
}