/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
//Buka Fail XML, Multi Stage, Perulangan - 21523244
package masukfxml;

import HalamanPengurus.HalamanPengurusController;
import masukData.MasukInterface;
import masukData.Masuk;
import RadioButton.RadioButtonController;
import IsiData.IsiDataController1;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Buka Fail XML, Multi Stage, Perulangan - 21523244
 */
public class FXMLControllerUtama implements Initializable {
   
   MasukInterface b = new Masuk();
   MasukInterface a = new Masuk();
    
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPUtama;
    
// Multi Stage    
    @FXML
    private void SignUpAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IsiDataController1.class.getResource("FXMLDocIsiData.fxml")); 
        //Praktikum1 diganti dengan nama file controller untuk FXML yang kedua
        // FXML2 diganti dengan FXML yang kedua

        Stage dialogStage = new Stage(); 
        dialogStage.setTitle("testing SignUp");
        dialogStage.initModality(Modality.APPLICATION_MODAL); 
        Scene scene = new Scene(loader.load()); 
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
        
    }
// Percabangan, Multi Stage    
    @FXML
    private void LoginAction(ActionEvent event) throws IOException {
        
        String Temp1 = tfEmail.getText();
        String Temp2 = tfPUtama.getText();

    XStream xstream = new XStream (new StaxDriver());
    FileInputStream cobi = null;
        try {
        // nama file yang akan dibuka (termasuk folder jika perlu
            cobi = new FileInputStream(tfEmail.getText()+"datapendaftar.xml");
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
        
            
            String Email = b.getEmailReg().toString();
            String PasswordUtama = b.getPasswordUtamaReg().toString();
            String PP = b.getPasswordPengurusReg().toString();
            String Nama = b.getNamaOr().toString();
            String Alamat = b.getAlamatOr().toString();
            a.setRegistrasi(Email, PasswordUtama, PP, Nama, Alamat);

            boolean masuk = false;
            if (Temp1.equals(Email) && Temp2.equals(PasswordUtama)){
                masuk = true;
                System.out.println("Email & Password Benar");
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(RadioButtonController.class.getResource("RadioButtonDoc.fxml")); 
                //Praktikum1 diganti dengan nama file controller untuk FXML yang kedua
                // FXML2 diganti dengan FXML yang kedua
                Stage dialogStage = new Stage(); 
                dialogStage.setTitle("testing Login");
                dialogStage.initModality(Modality.APPLICATION_MODAL); 
                Scene scene = new Scene(loader.load()); 
                dialogStage.setScene(scene);
                // Show the dialog and wait until the user closes it 
                dialogStage.showAndWait();   
            }else{
                masuk = false;
                if (!Temp1.equals(Email) && Temp2.equals(PasswordUtama)) {
                    System.out.println("Email salah");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert");
                    alert.setContentText("Email salah, coba masukkan lagi!");
                    Optional<ButtonType> result = alert.showAndWait();
                }
                else if(Temp1.equals(Email) && !Temp2.equals(PasswordUtama)){
                    System.out.println("Password Utama salah");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert");
                    alert.setContentText("Password Utama salah, coba masukkan lagi!");
                    Optional<ButtonType> result = alert.showAndWait();
                }else {
                    System.out.println("Email & Password Utama salah");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert");
                    alert.setContentText("Email & Password Utama salah, coba masukkan lagi!");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }
        
        
        String xml = xstream.toXML(a);
        FileOutputStream coba = null;
        try {
            coba = new FileOutputStream("sementara.xml");
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        OpenXML();
    }    
    
}
