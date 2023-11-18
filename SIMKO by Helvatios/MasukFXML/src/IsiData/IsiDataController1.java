/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
//Multi Stage,Program fail XML simpanBuka - 21523244
package IsiData;

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
import Verifikasi.VerifikasiController;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import masukData.Masuk;
import masukData.MasukInterface;

/**
 * FXML Controller class
 *
 * @author Multi Stage,Program fail XML simpanBuka - 21523244
 */
public class IsiDataController1 implements Initializable {
    
    MasukInterface b = new Masuk();

    XStream xstream = new XStream (new StaxDriver());

    @FXML
    private Button bKembali, bNext;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPasswordUtama;
    @FXML
    private PasswordField tfPasswordPengurus;
    @FXML
    private TextField tfNamaOr;
    @FXML
    private TextField tfAlamatOr;
    
    void OpenXML (){
        String a = b.getEmailReg();
        FileInputStream cobi = null;
        try {
        // nama file yang akan dibuka (termasuk folder jika perlu
            cobi = new FileInputStream(a+"datapendaftar.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya ="";
            while ((isi = cobi.read()) != -1) {
                charnya= (char) isi;
                stringnya = stringnya + charnya;
            }    
            b = (MasukInterface) xstream.fromXML(stringnya);	  
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
        System.out.println(b);
    }
    
    @FXML
    private void CloseAction(ActionEvent event) {
        Stage primaryStage = (Stage) bKembali.getScene().getWindow();
        primaryStage.close();
    }
    // Multi Stage
    @FXML
    private void LanjutAction(ActionEvent event) throws IOException {
      
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VerifikasiController.class.getResource("FXMLDocVerifikasi.fxml")); 
        //Praktikum1 diganti dengan nama file controller untuk FXML yang kedua
        // FXML2 diganti dengan FXML yang kedua

        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing SignUp");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
        
        Stage primaryStage = (Stage) bNext.getScene().getWindow();
        primaryStage.close();
    }
// Simpan XML
        @FXML
    private void hbSimpan (ActionEvent event) {
        
        String Temp1 = tfEmail.getText();
        String Temp2 = tfPasswordUtama.getText();
        String Temp3 = tfPasswordPengurus.getText();
        String Temp4 = tfNamaOr.getText();
        String Temp5 = tfAlamatOr.getText();
        b.setRegistrasi(Temp1, Temp2, Temp3, Temp4, Temp5);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText("Data Telah Tersimpan");
        Optional<ButtonType> result = alert.showAndWait();
        
        String xml = xstream.toXML(b);
        FileOutputStream coba = null;
        try {
            coba = new FileOutputStream(tfEmail.getText()+"datapendaftar.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            coba.write(bytes);
        } 
        catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
          } 
        finally {
            if (coba != null) {
                try {
                    coba.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } 
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OpenXML();
    }    
    
}
