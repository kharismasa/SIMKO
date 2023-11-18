/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
//Abstraksi (Interface), multiStage, Simpan Buka XML, percabangan - 21523244
package PasswordPengurus;

import HalamanPengurus.HalamanPengurusController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import RadioButton.RadioButtonController;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.util.Optional;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import masukData.Masuk;
import masukData.MasukInterface;

/**
 * FXML Controller class
 *
 * @author Abstraksi (Interface), multiStage, Simpan Buka XML, Percabangan - 21523244
 */
public class PasswordPengurusController implements Initializable {
// Abstraksi -> Termasuk mengimplementasikan Solid (Open Closed)
    MasukInterface a = new Masuk();
    
    @FXML
    private Button cancel;
    @FXML
    private PasswordField tfMasukkanPasswordPengurus;
// Multi Stage    
    @FXML
    private void CloseAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RadioButtonController.class.getResource("RadioButtonDoc.fxml")); 

        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing SignUp");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        
        Stage primaryStage = (Stage) cancel.getScene().getWindow();
        primaryStage.close();
    }
    
    @FXML
    private void NextAction(ActionEvent event) throws IOException {
        
        String Temp1 = tfMasukkanPasswordPengurus.getText();
        
        String PasswordPengurus = a.getPasswordPengurusReg().toString();
        boolean masuk = false;
// Percabangan - 21523244
        if (Temp1.equals(PasswordPengurus)){
            masuk = true;
            System.out.println("Berhasil Login");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HalamanPengurusController.class.getResource("HalamanPengurus.fxml")); 

            Stage dialogStage = new Stage(); 
            dialogStage.setTitle("testing SignUp");
            dialogStage.initModality(Modality.APPLICATION_MODAL); 
            Scene scene = new Scene(loader.load()); 
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            
        }else{
            masuk = false;
            System.out.println("Password Pengurus salah");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Password Pengurus salah, coba masukkan lagi!");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    
// Program File XML Buka    
     XStream xstream = new XStream (new StaxDriver());
        void OpenXML (){
        FileInputStream cobi = null;
        try {
            cobi = new FileInputStream("sementara.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya ="";
            while ((isi = cobi.read()) != -1) {
                charnya= (char) isi;
                stringnya = stringnya + charnya;
            }    
            a = (MasukInterface) xstream.fromXML(stringnya);	  
            }
        catch (Exception e){
            System.err.println("test: "+e.getMessage());
        }
        finally{
            if(cobi != null){
                try{
                    cobi.close();
                }
                catch (IOException e) {
                        e.printStackTrace();
                }
            }      
        } 
        System.out.println(a);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OpenXML();
    }    
    
}
