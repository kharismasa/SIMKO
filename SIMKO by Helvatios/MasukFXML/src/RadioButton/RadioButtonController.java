/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
//Komponen Dasar GUI (Radio Button),MultiStage - 21523244
package RadioButton;

import PasswordPengurus.PasswordPengurusController;
import HalamanAnggota.HalamanAnggotaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 21523244
 */
public class RadioButtonController implements Initializable {
    
    @FXML
    private Label l1;
    @FXML
    private Button button1;
    @FXML
    private RadioButton rabu1;
    @FXML
    private RadioButton rabu2;
    
//Radio Button - 21523244
    @FXML
    private void AnggotaAction(ActionEvent event) throws IOException {
// Multi Stage - 21523244   
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HalamanAnggotaController.class.getResource("HalamanAnggota.fxml")); 

            Stage dialogStage = new Stage(); 
            dialogStage.setTitle("testing Halaman Anggota");
            dialogStage.initModality(Modality.APPLICATION_MODAL); 
            Scene scene = new Scene(loader.load()); 
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            
        Stage primaryStage = (Stage) rabu1.getScene().getWindow();
        primaryStage.close();
    } 
//Radio Button - 21523244    
    @FXML
    private void PengurusAction(ActionEvent event) throws IOException {
// Multi Stage - 21523244    
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PasswordPengurusController.class.getResource("PasswordPengurusDoc.fxml")); 

            Stage dialogStage = new Stage(); 
            dialogStage.setTitle("testing Password Pengurus");
            dialogStage.initModality(Modality.APPLICATION_MODAL); 
            Scene scene = new Scene(loader.load()); 
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        
        Stage primaryStage = (Stage) rabu2.getScene().getWindow();
        primaryStage.close();
    }
    
    @FXML
    private void CloseAction(ActionEvent event) {
        Stage primaryStage = (Stage) button1.getScene().getWindow();
        primaryStage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
