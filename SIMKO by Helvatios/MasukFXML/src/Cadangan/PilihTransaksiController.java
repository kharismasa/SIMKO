/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Cadangan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PilihTransaksiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button bPendapatan;
    @FXML
    private Button bPengeluaran;
    @FXML
    private AnchorPane AP;
    
    @FXML
     private void PilihPendapatanAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Pendapatan.fxml"));
        AP.getChildren().setAll(pane);
     }
    @FXML
     private void PilihPengeluaranAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Pengeluaran.fxml"));
        AP.getChildren().setAll(pane);
     }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
